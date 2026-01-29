package com.tiba.backend.product.service;

import com.tiba.backend.exception.DataAlreadyExistsException;
import com.tiba.backend.exception.FieldErrorResponse;
import com.tiba.backend.exception.ValidationException;
import com.tiba.backend.product.dto.request.CategoryRequest;
import com.tiba.backend.product.entity.Category;
import com.tiba.backend.product.mapper.CategoryMapper;
import com.tiba.backend.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;

  @Override
  public Long createCategory(CategoryRequest request) {
    validateUniqueFields(null, request);
    Category category = categoryMapper.toEntity(request);
    return categoryRepository.save(category).getId();
  }

  private void validateUniqueFields(Category currentCategory, CategoryRequest request) {
    List<FieldErrorResponse> errors = new ArrayList<>();
    if ((currentCategory == null || !currentCategory.getName().equals(request.name()))
        && categoryRepository.existsByName(request.name())) {
      errors.add(new FieldErrorResponse("name", "CATEGORY_NAME_ALREADY_EXISTS"));
    }

    if (!errors.isEmpty()) {
      throw new ValidationException(errors);
    }
  }
}
