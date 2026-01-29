package com.tiba.backend.product.service;

import com.tiba.backend.product.dto.request.ProductRequest;
import com.tiba.backend.product.dto.response.ProductResponse;
import com.tiba.backend.shared.PageResponse;

import java.util.List;

public interface ProductService {

  Long createProduct(ProductRequest request);

  ProductResponse getProductById(Long productId);

  List<ProductResponse> getAllProducts();

  PageResponse<ProductResponse> getAllWithPagination(int page, int size);

  void deleteProduct(Long productId);
}
