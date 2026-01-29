package com.tiba.backend.product.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
    @NotBlank(message = "PRODUCT_NAME_REQUIRED") String name,
    @NotBlank(message = "DESCRIPTION_NAME_REQUIRED") String description,
    @NotBlank(message = "PRODUCT_CODE_REQUIRED") String code,
    @NotNull(message = "PRODUCT_PRICE_REQUIRED")
        @Positive(message = "PRODUCT_PRICE_MUST_BE_POSITIVE")
        BigDecimal price,
    @NotNull(message = "DISCOUNT_STATUS_REQUIRED") Boolean discounted,
    @NotNull(message = "CATEGORY_ID_REQUIRED") Long categoryId) {}
