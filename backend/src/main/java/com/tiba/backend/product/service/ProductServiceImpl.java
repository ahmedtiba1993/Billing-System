package com.tiba.backend.product.service;

import com.tiba.backend.exception.DataAlreadyExistsException;
import com.tiba.backend.exception.FieldErrorResponse;
import com.tiba.backend.exception.ResourceNotFoundException;
import com.tiba.backend.exception.ValidationException;
import com.tiba.backend.product.dto.request.ProductRequest;
import com.tiba.backend.product.dto.response.ProductResponse;
import com.tiba.backend.product.entity.Category;
import com.tiba.backend.product.entity.Product;
import com.tiba.backend.product.mapper.ProductMapper;
import com.tiba.backend.product.repository.CategoryRepository;
import com.tiba.backend.product.repository.ProductRepository;
import com.tiba.backend.shared.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;
  private final ProductMapper productMapper;

  @Override
  public Long createProduct(ProductRequest request) {

    List<FieldErrorResponse> errors = new ArrayList<>();

    if (productRepository.existsByCode(request.code())) {
      errors.add(new FieldErrorResponse("code", "Product code already exists"));
    }

    Category category = categoryRepository.findById(request.categoryId()).orElse(null);
    if (category == null) {
      errors.add(new FieldErrorResponse("categoryId", "Category not found"));
    }

    if (!errors.isEmpty()) {
      throw new ValidationException(errors);
    }

    return productRepository.save(productMapper.toEntity(request, request.categoryId())).getId();
  }

  @Override
  @Transactional(readOnly = true)
  public ProductResponse getProductById(Long productId) {
    Product product =
        productRepository
            .findById(productId)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

    return productMapper.toResponse(product);
  }

  @Override
  @Transactional(readOnly = true)
  public List<ProductResponse> getAllProducts() {
    return productRepository.findAll().stream().map(productMapper::toResponse).toList();
  }

  @Override
  @Transactional(readOnly = true)
  public PageResponse<ProductResponse> getAllWithPagination(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<ProductResponse> productPage =
        productRepository.findAll(pageable).map(productMapper::toResponse);

    return PageResponse.from(productPage);
  }

  @Override
  @Transactional
  public void deleteProduct(Long productId) {
    if (!productRepository.existsById(productId)) {
      throw new ResourceNotFoundException("Product not found with id: " + productId);
    }
    productRepository.deleteById(productId);
  }
}
