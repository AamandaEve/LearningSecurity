package com.learning.security.configs;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.learning.security.models.entities.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
public class TokenService {

  @Value("${api.security.token.secret}")
  private String secret;

  /*
   * passar um usuário para que o
   * service verifique se o usuário
   *  que enviou o token possui a
   * role necessária
   * */
  public String generateToken(User user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      String token = JWT.create()
              .withIssuer("auth-api")//nome que identifica a aplicação
              .withSubject(user.getLogin())//salvar o login do usuário no token
              .withExpiresAt(generateExpirationDate())
              .sign(algorithm);//fazer assinatura e geração final
      return token;
    } catch (JWTCreationException exception) {
      throw new RuntimeException("Erro ao gerar token" + exception);
    }
  }

  public String validateToken(String token){
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);

      return JWT.require(algorithm)
              .withIssuer("auth-api")
              .build()//montando novamente o dado que tem dentro
              .verify(token)//descriptografando o token
              .getSubject();//e pegando o usuario que esta dentro dele(usuario que enviou o token(ou não))
    } catch (JWTVerificationException e) {
      return "";
    }
  }

  public Instant generateExpirationDate(){
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }
}
