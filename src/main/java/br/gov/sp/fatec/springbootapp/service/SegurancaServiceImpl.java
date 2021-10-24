package br.gov.sp.fatec.springbootapp.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.springbootapp.entity.Autorizacao;
import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.exception.RegistroNaoEncontradoException;
import br.gov.sp.fatec.springbootapp.repository.AutorizacaoRepository;
import br.gov.sp.fatec.springbootapp.repository.UsuarioRepository;

@Service("segurancaService")
public class SegurancaServiceImpl implements SegurancaService {

  @Autowired
  private AutorizacaoRepository autRepo;

  @Autowired
  private UsuarioRepository usuarioRepo;

  @Autowired
  private PasswordEncoder passEncoder;

  @Transactional
  public Usuario criarUsuario(String nome, String senha, String autorizacao) {
    Autorizacao aut = autRepo.findByNome(autorizacao);
    if (aut == null) {
      aut = new Autorizacao();
      aut.setNome(autorizacao);
      autRepo.save(aut);
    }
    Usuario usuario = new Usuario();
    usuario.setNome(nome);
    usuario.setSenha(passEncoder.encode(senha));
    usuario.setAutorizacoes(new HashSet<Autorizacao>());
    usuario.getAutorizacoes().add(aut);
    usuarioRepo.save(usuario);
    return usuario;
  }

  @Override
  @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
  // PreAuthorize é um annotation para proteger as rotas necessárias de uma autorização
  public List<Usuario> buscarTodosUsuarios() {
    return usuarioRepo.findAll();
  }

  @Override
  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  // hasAnyRole serve para autorizar a rota para mais de um tipo de autorização
  public Usuario buscarUsuarioPorId(Long id) {
    Optional<Usuario> usuarioOp = usuarioRepo.findById(id);
    if (usuarioOp.isPresent()) {
      return usuarioOp.get();
    }
    throw new RegistroNaoEncontradoException("Usuário não encontrado!");
  }

  @Override
  @PreAuthorize("isAuthenticated()")
  // isAuthenticated é somente para verificar se o usuario está logado, sem dar importação para as autorizações
  public Usuario buscarUsuarioPorNome(String nome) {
    Usuario usuario = usuarioRepo.findByNome(nome);
    if (usuario != null) {
      return usuario;
    }
    throw new RegistroNaoEncontradoException("Usuário não encontrado!");

  }

  @Override
  @PreAuthorize("isAuthenticated()")
  public Autorizacao buscarAutorizacaoPorNome(String nome) {
    Autorizacao autorizacao = autRepo.findByNome(nome);
    if (autorizacao != null) {
      return autorizacao;
    }
    throw new RegistroNaoEncontradoException("Autorização não encontrada!");
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // recebendo o nome de usuario vou verificar se esse usuario existe no meu banco de dados
    Usuario usuario = usuarioRepo.findByNome(username);
    if (usuario == null) {
      throw new UsernameNotFoundException("Usuário " + username + " não encontrado!");
      // se não foi achado um usuario é dado uma exceção
    }
    // Caso contrario será utilizado o objeto User do spring security para montar um objeto UserDetails
    return User.builder().username(username).password(usuario.getSenha())
        // seto o nome e a senha do usuario
        .authorities(usuario.getAutorizacoes().stream()
        // 
            .map(Autorizacao::getNome).collect(Collectors.toList())
            // Vetor de Autorizações onde cada item do vetor é uma autorização do usuário
            .toArray(new String[usuario.getAutorizacoes().size()]))
            // Essa contrução está pegando a lista de autorizações e está mapeando para um vetor de strings
        .build();
  }

}