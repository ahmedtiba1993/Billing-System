package com.tiba.backend.shared;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tiba.backend.exception.FieldErrorResponse;

import java.time.Instant;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(
    boolean success,
    String message,
    String code,
    T data,
    List<FieldErrorResponse> errors,
    Instant timestamp) {

  public static <T> ApiResponse<T> success(T data, String message) {
    return new ApiResponse<>(true, message, "SUCCESS", data, null, Instant.now());
  }

  public static ApiResponse<Void> validationError(List<FieldErrorResponse> errors) {
    return new ApiResponse<>(
        false, "Validation failed", "VALIDATION_ERROR", null, errors, Instant.now());
  }

  public ApiResponse(boolean success, String message, String code, Instant timestamp) {
    this(success, message, code, null, null, timestamp);
  }
}
