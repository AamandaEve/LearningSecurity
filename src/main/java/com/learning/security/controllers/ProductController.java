package com.learning.security.controllers;

import com.learning.security.models.dtos.ProductDTO;
import com.learning.security.services.ProductService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void save(@RequestBody ProductDTO productDTO){
    productService.save(productDTO);
  }

  @PutMapping("/{productId}")
  @ResponseStatus(HttpStatus.CREATED)
  public void edit(@PathVariable("productId") UUID productId, @RequestBody ProductDTO productDTO){
    productService.edit(productId, productDTO);
  }

  @DeleteMapping("/{productId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("productId") UUID productId){
    productService.delete(productId);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.ACCEPTED)
  public Page<ProductDTO> findAll(Pageable pageable){
    return productService.findAll(pageable);
  }

  @GetMapping("/{productId}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public Optional<ProductDTO> findById(@PathVariable("/{productId}") UUID productId){
    return productService.findById(productId);
  }

}
