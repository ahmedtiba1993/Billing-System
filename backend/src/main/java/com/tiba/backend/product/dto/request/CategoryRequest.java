package com.tiba.backend.product.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
    @NotBlank(message = "CATEGORY_NAME_REQUIRED") String name,
    @NotBlank(message = "CATEGORY_DESCRIPTION_REQUIRED") String description) {}
