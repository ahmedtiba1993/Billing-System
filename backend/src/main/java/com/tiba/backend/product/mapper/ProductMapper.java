package com.tiba.backend.product.mapper;

import com.tiba.backend.product.dto.request.ProductRequest;
import com.tiba.backend.product.dto.response.ProductResponse;
import com.tiba.backend.product.entity.Category;
import com.tiba.backend.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductMapper {

  public Product toEntity(ProductRequest request, Long categoryId) {
    Product product = new Product();
    product.setName(request.name());
    product.setDescription(request.description());
    product.setCode(request.code());
    product.setPrice(request.price());
    product.setDiscounted(request.discounted());
    product.setCategory(Category.builder().id(categoryId).build());
    return product;
  }

  public ProductResponse toResponse(Product product) {
    if (product == null) {
      return null;
    }

    return new ProductResponse(
        product.getId(),
        product.getName(),
        product.getDescription(),
        product.getCode(),
        product.getPrice(),
        product.isDiscounted());
  }
}
