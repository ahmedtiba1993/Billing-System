package com.tiba.backend.product.dto.response;

import java.math.BigDecimal;

public record ProductResponse(
    Long id, String name, String description, String code, BigDecimal price, boolean discounted) {}
