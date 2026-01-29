package com.tiba.backend.product.controller;

import com.tiba.backend.product.dto.request.ProductRequest;
import com.tiba.backend.product.dto.response.ProductResponse;
import com.tiba.backend.product.service.ProductService;
import com.tiba.backend.shared.ApiResponse;
import com.tiba.backend.shared.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<ApiResponse<Long>> create(@Valid @RequestBody ProductRequest request) {
    Long response = productService.createProduct(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(response));
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
  public ResponseEntity<ApiResponse<ProductResponse>> getById(@PathVariable("id") Long productId) {
    ProductResponse response = productService.getProductById(productId);
    return ResponseEntity.ok(ApiResponse.success(response));
  }

  @GetMapping
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  public ResponseEntity<ApiResponse<PageResponse<ProductResponse>>> getAllWithPagination(
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "10") int size) {
    PageResponse<ProductResponse> response = productService.getAllWithPagination(page, size);
    return ResponseEntity.ok(ApiResponse.success(response));
  }

  @GetMapping("/all")
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProducts() {
    List<ProductResponse> response = productService.getAllProducts();
    return ResponseEntity.ok(ApiResponse.success(response));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable("id") Long productId) {
    productService.deleteProduct(productId);
    return ResponseEntity.ok(ApiResponse.deleted());
  }
}
