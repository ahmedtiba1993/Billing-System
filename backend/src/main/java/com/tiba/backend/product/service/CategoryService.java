package com.tiba.backend.product.service;

import com.tiba.backend.product.dto.request.CategoryRequest;

public interface CategoryService {

  Long createCategory(CategoryRequest request);
}
