package com.tiba.backend.customer.repository;

import com.tiba.backend.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

  boolean existsByCustomerCode(String customerCode);

  boolean existsByEmail(String email);

  boolean existsByBusinessName(String businessName);
}
