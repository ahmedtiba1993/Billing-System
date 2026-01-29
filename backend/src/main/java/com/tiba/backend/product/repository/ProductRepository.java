package com.tiba.backend.product.repository;

import com.tiba.backend.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
  boolean existsByCode(String code);
}
