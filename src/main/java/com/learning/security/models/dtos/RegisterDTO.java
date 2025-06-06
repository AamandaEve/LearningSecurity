package com.learning.security.models.dtos;

import com.learning.security.models.entities.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO extends AuthenticationDTO{
  private UserRole role;
}
