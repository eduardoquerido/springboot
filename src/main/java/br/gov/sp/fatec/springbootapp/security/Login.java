package br.gov.sp.fatec.springbootapp.security;

public class Login {

  private String username;

  private String password;

  private String autorizacao;

  private String token;

  // O front irá fazer o login e irá receber o usuario, autorizacao e o token para ser utilizado no sistema
  // Sempre que for pedir algo ao backend o front end irá utilizar esse token
  
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getAutorizacao() {
    return autorizacao;
  }

  public void setAutorizacao(String autorizacao) {
    this.autorizacao = autorizacao;
  }
    
}