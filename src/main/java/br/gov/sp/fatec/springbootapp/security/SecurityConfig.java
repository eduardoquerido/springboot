package br.gov.sp.fatec.springbootapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity  // Habilita as configurações padrões de segurança do spring security
@EnableGlobalMethodSecurity(prePostEnabled = true)  // Habilita a parte de segurança por anotações
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService; // Serviço que irá buscar os dados do Usuário no banco de dados

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()  // aqui estamos desabilitando o esquema de token do security que é muito antiga
        .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); 
        // Stateless por que nós estamos fazendo REST e REST não se guarda informações de uma requisição para outra.
        // Ou seja, quando terminar a requisição é feito o logout e toda a parte de segurança é jogada fora.

        // Filter --> O FILTRO é um código que vai interceptar requisições, então TODA requisição irá passar por esse cara.
        // JwtAuthenticationFilter --> esse cara irá cuidar do token jwt, pois o spring security não consegue fazer login utilizando token

        // Antes de entrar no spring security, a requisição vai entrar no filtro (JwtAuthenticationFilter) que vai lidar com o token JWT, que é um json.
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
  }

  @Bean
  public PasswordEncoder passwordEncoderBean() {
    return new BCryptPasswordEncoder();  // Encode, nosso hash para o password é o BCrypt
  }

  // Abaixo está setado a parte de autenticação do spring security que poderá ser utilizado num controller
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

}