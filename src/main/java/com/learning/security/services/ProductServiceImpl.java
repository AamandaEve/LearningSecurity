package com.learning.security.services;

import com.learning.security.models.dtos.ProductDTO;
import com.learning.security.models.entities.Product;
import com.learning.security.models.mappers.ProductMapper;
import com.learning.security.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  @Override
  @Transactional
  public void save(ProductDTO productDTO) {
    productRepository.save(productMapper.toEntity(productDTO));
  }

  @Override
  @Transactional
  public void edit(UUID productId, ProductDTO productDTO) {
    productDTO.setId(null);
    productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    Product product = productMapper.toEntity(productDTO);
    product.setId(productId);
    productRepository.save(product);
  }

  @Override
  @Transactional
  public void delete(UUID productId) {
    productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    productRepository.deleteById(productId);
  }

  @Override
  public Page<ProductDTO> findAll(Pageable pageable) {
    return productRepository.findAll(pageable).map(productMapper::toDTO);
  }

  @Override
  public Optional<ProductDTO> findById(UUID id) {
    return productRepository.findById(id).map(ProductMapper.INSTANCE::toDTO);
  }
}
