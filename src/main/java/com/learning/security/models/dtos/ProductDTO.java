package com.learning.security.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
  private UUID id;
  private String name;
  private int quantity;
  private String barcode;
}
