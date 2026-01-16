package com.tiba.backend.exception;

import com.tiba.backend.auth.dto.AuthResponse;
import com.tiba.backend.shared.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<AuthResponse> handleBadCredentials() {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(new AuthResponse(false, "Invalid username or password", "AUTH_001"));
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<AuthResponse> handleAccessDenied() {
    return ResponseEntity.status(HttpStatus.FORBIDDEN)
        .body(new AuthResponse(false, "Access denied: insufficient permissions", "AUTH_005"));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<AuthResponse> handleGlobal() {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new AuthResponse(false, "An internal server error occurred", "SYS_001"));
  }

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ApiResponse<Void>> handleValidation(ValidationException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ApiResponse.validationError(ex.getErrors()));
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiResponse<Void>> handleNotFound(ResourceNotFoundException ex) {

    ApiResponse<Void> response =
        new ApiResponse<>(false, ex.getMessage(), ex.getCode(), null, null, Instant.now());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @ExceptionHandler(io.jsonwebtoken.ExpiredJwtException.class)
  public ResponseEntity<AuthResponse> handleExpiredJwt(io.jsonwebtoken.ExpiredJwtException ex) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN)
        .body(new AuthResponse(false, "Token has expired", null, "AUTH_002", LocalDateTime.now()));
  }

  @ExceptionHandler(io.jsonwebtoken.JwtException.class)
  public ResponseEntity<AuthResponse> handleJwtException(io.jsonwebtoken.JwtException ex) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN)
        .body(new AuthResponse(false, "Invalid token", null, "AUTH_003", LocalDateTime.now()));
  }
}
