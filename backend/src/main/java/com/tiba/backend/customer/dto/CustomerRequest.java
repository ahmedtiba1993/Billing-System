package com.tiba.backend.customer.dto;

import jakarta.validation.constraints.*;

public record CustomerRequest(
    @NotBlank(message = "FIRST_NAME_REQUIRED")
        @Size(min = 3, max = 50, message = "FIRST_NAME_SIZE_INVALID")
        String firstName,
    @NotBlank(message = "LAST_NAME_REQUIRED")
        @Size(min = 3, max = 50, message = "LAST_NAME_SIZE_INVALID")
        String lastName,
    @NotBlank(message = "BUSINESS_NAME_REQUIRED")
        @Size(min = 3, message = "BUSINESS_NAME_MIN_3_CHARS")
        String businessName,
    @NotBlank(message = "ADDRESS_REQUIRED") @Size(min = 3, message = "ADDRESS_MIN_3_CHARS")
        String address,
    @NotBlank(message = "PHONE_NUMBER_REQUIRED")
        @Pattern(regexp = "^[0-9+ ]{8,20}$", message = "INVALID_PHONE_NUMBER")
        String phoneNumber,
    @NotBlank(message = "CUSTOMER_CODE_REQUIRED") String customerCode,
    @NotBlank(message = "EMAIL_REQUIRED") @Email(message = "INVALID_EMAIL_FORMAT") String email,
    @NotNull(message = "DISCOUNT_REQUIRED")
        @Min(value = 0, message = "DISCOUNT_MIN_0")
        @Max(value = 100, message = "DISCOUNT_MAX_100")
        Integer discount) {}
