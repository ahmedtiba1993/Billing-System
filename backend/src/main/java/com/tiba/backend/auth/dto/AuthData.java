package com.tiba.backend.auth.dto;

public record AuthData(String token, String type, long expiresIn, UserInfo user) {}
