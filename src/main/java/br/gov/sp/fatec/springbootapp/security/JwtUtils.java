package br.gov.sp.fatec.springbootapp.security;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtils {

  private static final String KEY = "qqig(9om4!p7i*0dl@zxa@6h^#nfx8mjp$b1mnjwun7qfmm4#^";
  // essa KEY é utilizada para gerar o token
  // Ela deve ser uma string de "sopa de letrinhas" contendo 512 bits pois está sendo utilizado o Algoritmo HS512
  // o ideal é que essa KEY fique em um arquivo de configurações

  public static String generateToken(Authentication usuario) throws JsonProcessingException {
    // O generateToken irá receber um objeto de Autenticação que tem usuário, senha e permissões
    ObjectMapper mapper = new ObjectMapper();
    // Mapper gera jsons
    Login usuarioSemSenha = new Login(); // Criando um objeto do tipo Login
    usuarioSemSenha.setUsername(usuario.getName()); // Vou setar esse novo objeto com username igual ao nome do usuário recebido
    if (!usuario.getAuthorities().isEmpty()) {
      usuarioSemSenha.setAutorizacao(usuario.getAuthorities().iterator().next().getAuthority());
      // Aqui vai ser setados as autorizações do usuario (nesse caso está sendo utilizado somente a primeira)
    }
    String usuarioJson = mapper.writeValueAsString(usuarioSemSenha);
    // Acima estou serializando o objeto criado do usuario e vou jogar isso para dentro do token
    Date agora = new Date();
    Long hora = 1000L * 60L * 60L; // Uma hora em milissegundos
    // duração do token é de 1h, então "agora" + hora (1h em milisegundos)
    return Jwts.builder().claim("userDetails", usuarioJson).setIssuer("br.gov.sp.fatec").setSubject(usuario.getName())  // Aqui estou utilizando a biblioteca Jwts para criar o Token
        // userDetails é um atributo json que será preenchido com as informações do usuário para logar
        // setIssuer é quem está gerando esse token
        // setSubject é para quem estou gerando esse token, no caso para o usuario que está fazendo o login
        .setExpiration(new Date(agora.getTime() + hora)).signWith(SignatureAlgorithm.HS512, KEY).compact();  // Aqui está sendo assinado o token com o algoritmo de criptogradia + a minha chave (KEY)
        // compact irá pegar esse token que é um JSON e irá transformar em uma string só e irá passar o base64 para poder trafegar com ele na internet
  }

  public static Authentication parseToken(String token) throws JsonParseException, JsonMappingException, IOException {
    ObjectMapper mapper = new ObjectMapper();  // Precisaremos no mapper novamente para pegar o json que gerou do usuario e trasnformar de volta em um objeto Login
    String credentialsJson = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody().get("userDetails",
        String.class);
        // No parser vou passar minha chave (KEY) e meu TOKEN
        // O parser vai abrir o token e vai validar se o token está adulterado, expirado e outras verficações
        // Se não lancçou nenhuma exceção, o parser pega o corpo do token
    Login usuario = mapper.readValue(credentialsJson, Login.class);
    // Com o atributo credentialsJson será transformado denovo na classe Login
    UserDetails userDetails = User.builder().username(usuario.getUsername()).password("secret")
        .authorities(usuario.getAutorizacao()).build();
    // Com base na classe Login, será gerado um objeto do tipo UserDetails
    return new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword(),
        userDetails.getAuthorities());
    // Com o objeto do tipo UserDetails é gerado outro objeto que é o UsernamePasswordAuthenticationToken que terá o nome de usuáro, senha e autorizações
    // E é esse objeto que que será utilizado para fazer login lá no JwtAuthenticationFilter
  }

}