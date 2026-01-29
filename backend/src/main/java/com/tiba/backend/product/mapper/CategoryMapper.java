package com.tiba.backend.product.mapper;

import com.tiba.backend.product.dto.request.CategoryRequest;
import com.tiba.backend.product.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryMapper {

  public Category toEntity(CategoryRequest request) {

    Category category = new Category();
    category.setName(request.name());
    category.setDescription(request.description());

    return category;
  }
}
