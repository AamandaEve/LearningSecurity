package com.learning.security.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
* Classe para desabilitar as configurações
* padrão do Spring Security e implementar
* a configuração desejada.
* */
@Configuration
@EnableWebSecurity//habilitar configuração de web security para configurarmos
public class SecurityConfigurations {

  /*
  * camada de filtro de segurança para validar
  * se um usuário está apto a realizar a requisição*/
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
            .csrf(csrf -> csrf.disable())
            .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //setar que a política da sessão é stateless, ou seja, não guarda estado, apenas o token para liberar as proximas requisições
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                    .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                    .requestMatchers(HttpMethod.POST, "/produto").hasRole("ADMIN")//metodos post em produto deve ter permissão de admin
                    .anyRequest().authenticated())//qualquer outro método a unica requisição é que esteja autenticado
            .build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }//metodo apenas para pegar a instancia do authenticationManager no controller

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }//informa ao spring que ele deve criptografar as senhas

}
