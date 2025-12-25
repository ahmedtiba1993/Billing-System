package com.tiba.backend.auth.dto;

import java.time.LocalDateTime;

public record AuthResponse(
    boolean success, String message, Object data, String errorCode, LocalDateTime timestamp) {}
