package com.tiba.backend.customer.mapper;

import com.tiba.backend.customer.dto.CustomerRequest;
import com.tiba.backend.customer.dto.CustomerResponse;
import com.tiba.backend.customer.entity.Customer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

  public Customer toEntity(CustomerRequest request) {
    if (request == null) return null;

    Customer customer = new Customer();
    apply(customer, request);
    return customer;
  }

  public CustomerResponse toResponse(Customer customer) {
    if (customer == null) return null;

    return new CustomerResponse(
        customer.getId(),
        customer.getFirstName(),
        customer.getLastName(),
        customer.getBusinessName(),
        customer.getAddress(),
        customer.getPhoneNumber(),
        customer.getCustomerCode(),
        customer.getEmail(),
        customer.getDiscount());
  }

  public void updateEntity(Customer customer, CustomerRequest request) {
    if (customer == null || request == null) return;
    apply(customer, request);
  }

  private void apply(Customer customer, CustomerRequest request) {
    customer.setFirstName(request.firstName());
    customer.setLastName(request.lastName());
    customer.setBusinessName(request.businessName());
    customer.setAddress(request.address());
    customer.setPhoneNumber(request.phoneNumber());
    customer.setCustomerCode(request.customerCode());
    customer.setEmail(request.email());
    customer.setDiscount(request.discount());
  }
}
