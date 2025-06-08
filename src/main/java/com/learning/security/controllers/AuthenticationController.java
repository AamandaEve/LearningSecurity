package com.learning.security.controllers;

import com.learning.security.configs.TokenService;
import com.learning.security.models.dtos.AuthenticationDTO;
import com.learning.security.models.dtos.LoginResponseDTO;
import com.learning.security.models.dtos.RegisterDTO;
import com.learning.security.models.entities.user.User;
import com.learning.security.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController{

  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;
  private final TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity login(@RequestBody AuthenticationDTO authenticationDTO){
    var usernamepassword = new UsernamePasswordAuthenticationToken(authenticationDTO.getName(), authenticationDTO.getPassword());//classe do proprio security, username e password em formato de token
    var auth = authenticationManager.authenticate(usernamepassword);//verificar se usuario e senha estão no banco, recebe como param um UsernamePasswordToken

    var token = tokenService.generateToken((User) auth.getPrincipal());

    return ResponseEntity.ok(new LoginResponseDTO(token));
  }

  @PostMapping("register")
  public ResponseEntity register(@RequestBody RegisterDTO registerDTO){
    if (userRepository.findByLogin(registerDTO.getName()) != null) return ResponseEntity.badRequest().build();

    String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.getPassword());

    User user = new User(registerDTO.getName(), encryptedPassword, registerDTO.getRole());

    userRepository.save(user);

    return ResponseEntity.ok().build();
  }
}

