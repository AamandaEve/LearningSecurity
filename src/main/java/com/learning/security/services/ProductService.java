package com.learning.security.services;

import com.learning.security.models.dtos.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ProductService {
  void save(ProductDTO productDTO);
  void edit(UUID productId, ProductDTO productDTO);
  void delete(UUID productId);
  Page<ProductDTO> findAll(Pageable pageable);
  Optional<ProductDTO> findById(UUID id);
}
