package com.tiba.backend.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

  private final String code;

  public ResourceNotFoundException(String message) {
    super(message);
    this.code = "NOT_FOUND";
  }
}
