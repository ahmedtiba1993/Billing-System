package com.tiba.backend.exception;

public class ResourceNotFoundException extends RuntimeException {

  private final String code;

  public ResourceNotFoundException(String message) {
    super(message);
    this.code = "NOT_FOUND";
  }

  public String getCode() {
    return code;
  }
}
