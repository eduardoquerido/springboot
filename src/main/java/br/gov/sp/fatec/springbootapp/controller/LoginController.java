package br.gov.sp.fatec.springbootapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.springbootapp.security.JwtUtils;
import br.gov.sp.fatec.springbootapp.security.Login;

@RestController
@RequestMapping(value = "/login")
@CrossOrigin  // CrossOrigin pois queremos utilizar o login via outra aplicação
public class LoginController {

  @Autowired
  private AuthenticationManager authManager;  // Aqui está configurado para o spring security fazer o login que está definido no arquivo de SecurityConfig
 
  @PostMapping()
  public Login autenticar(@RequestBody Login login) throws JsonProcessingException {
    // ele recebe um objeto do tipo Login
    Authentication auth = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
    // Com esse objeto do tipo Login vai ser gerado um objeto do tipo Authentication
    auth = authManager.authenticate(auth);
    // e utiliza o AuthenticationManager para fazer o login
    login.setPassword(null); // apaga a senha do usuário para não ter problema de segurança
    login.setToken(JwtUtils.generateToken(auth));
    // seta o token com base no objeto de autenticação que foi criado pelo AuthenticationManager
    return login;
  }
  
}