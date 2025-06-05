package com.learning.security.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
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
            .build();
  }
}
