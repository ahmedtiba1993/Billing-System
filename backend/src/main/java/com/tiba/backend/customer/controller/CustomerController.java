package com.tiba.backend.customer.controller;

import com.tiba.backend.customer.dto.CustomerRequest;
import com.tiba.backend.customer.dto.CustomerResponse;
import com.tiba.backend.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.tiba.backend.shared.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @PostMapping
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<ApiResponse<Long>> create(@Valid @RequestBody CustomerRequest request) {
    Long response = customerService.create(request);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ApiResponse.success(response, "Customer created successfully"));
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<ApiResponse<CustomerResponse>> getById(@PathVariable Long id) {
    CustomerResponse customer = customerService.getById(id);
    return ResponseEntity.ok(ApiResponse.success(customer, "Customer retrieved successfully"));
  }

  @GetMapping("/all")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<ApiResponse<List<CustomerResponse>>> getAll() {
    List<CustomerResponse> customers = customerService.getAll();
    return ResponseEntity.ok(ApiResponse.success(customers, "Customers retrieved successfully"));
  }

  @GetMapping("/page")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<ApiResponse<PageResponse<CustomerResponse>>> getAllWithPagination(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
    PageResponse<CustomerResponse> response = customerService.getAllWithPagination(page, size);
    return ResponseEntity.ok(ApiResponse.success(response, "Customers retrieved successfully"));
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<ApiResponse<CustomerResponse>> update(
      @PathVariable Long id, @Valid @RequestBody CustomerRequest request) {
    CustomerResponse response = customerService.update(id, request);
    return ResponseEntity.ok(ApiResponse.success(response, "Customer updated successfully"));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<ApiResponse<Void>> deleteCustomer(@PathVariable Long id) {
    customerService.delete(id);
    return ResponseEntity.ok(ApiResponse.success(null, "Customer deleted successfully"));
  }
}
