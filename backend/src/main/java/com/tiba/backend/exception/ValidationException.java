package com.tiba.backend.exception;

import java.util.List;

public class ValidationException extends RuntimeException {

  private final List<FieldErrorResponse> errors;

  public ValidationException(List<FieldErrorResponse> errors) {
    super("VALIDATION_ERROR");
    this.errors = errors;
  }

  public List<FieldErrorResponse> getErrors() {
    return errors;
  }
}
