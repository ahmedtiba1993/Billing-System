package com.tiba.backend.product.controller;

import com.tiba.backend.product.dto.request.CategoryRequest;
import com.tiba.backend.product.service.CategoryService;
import com.tiba.backend.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @PostMapping
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<ApiResponse<Long>> create(@Valid @RequestBody CategoryRequest request) {

    Long response = categoryService.createCategory(request);

    return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(response));
  }
}
