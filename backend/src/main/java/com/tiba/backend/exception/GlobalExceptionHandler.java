package com.tiba.backend.exception;

import com.tiba.backend.auth.dto.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<AuthResponse> handleBadCredentials() {

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(
            new AuthResponse(
                false, "Invalid username or password", null, "AUTH_001", LocalDateTime.now()));
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<AuthResponse> handleAccessDenied() {

    return ResponseEntity.status(HttpStatus.FORBIDDEN)
        .body(
            new AuthResponse(
                false,
                "Access denied: insufficient permissions",
                null,
                "AUTH_005",
                LocalDateTime.now()));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<AuthResponse> handleGlobal() {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
            new AuthResponse(
                false, "An internal server error occurred", null, "SYS_001", LocalDateTime.now()));
  }
}
