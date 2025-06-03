package com.learning.security.models.mappers;

import com.learning.security.models.dtos.ProductDTO;
import com.learning.security.models.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

  Product toEntity(ProductDTO productDTO);
  ProductDTO toDTO(Product product);
}
