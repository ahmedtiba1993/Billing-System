package com.tiba.backend.customer.dto;

public record CustomerResponse(
    Long id,
    String firstName,
    String lastName,
    String businessName,
    String address,
    String phoneNumber,
    String customerCode,
    String email,
    Integer discount) {}
