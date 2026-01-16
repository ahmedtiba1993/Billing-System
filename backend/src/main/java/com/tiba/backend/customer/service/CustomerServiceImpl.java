package com.tiba.backend.customer.service;

import com.tiba.backend.customer.dto.CustomerRequest;
import com.tiba.backend.customer.dto.CustomerResponse;
import com.tiba.backend.customer.entity.Customer;
import com.tiba.backend.customer.mapper.CustomerMapper;
import com.tiba.backend.customer.repository.CustomerRepository;
import com.tiba.backend.exception.DataAlreadyExistsException;
import com.tiba.backend.exception.FieldErrorResponse;
import com.tiba.backend.exception.ResourceNotFoundException;
import com.tiba.backend.exception.ValidationException;
import com.tiba.backend.shared.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final CustomerMapper customerMapper;

  @Override
  @Transactional
  public Long create(CustomerRequest request) {
    validateUniqueFields(null, request);
    Customer customer = customerMapper.toEntity(request);
    return customerRepository.save(customer).getId();
  }

  @Override
  @Transactional(readOnly = true)
  public CustomerResponse getById(Long id) {
    Customer customer =
        customerRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
    return customerMapper.toResponse(customer);
  }

  @Override
  @Transactional(readOnly = true)
  public List<CustomerResponse> getAll() {
    return customerRepository.findAll().stream().map(customerMapper::toResponse).toList();
  }

  @Override
  @Transactional(readOnly = true)
  public PageResponse<CustomerResponse> getAllWithPagination(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<CustomerResponse> customerPage =
        customerRepository.findAll(pageable).map(customerMapper::toResponse);
    return PageResponse.from(customerPage);
  }

  @Override
  @Transactional
  public CustomerResponse update(Long id, CustomerRequest request) {
    Customer customer = findEntityByIdOrThrow(id);
    validateUniqueFields(customer, request);
    customerMapper.updateEntity(customer, request);
    Customer saved = customerRepository.save(customer);
    return customerMapper.toResponse(saved);
  }

  @Override
  @Transactional
  public void delete(Long id) {
    if (!customerRepository.existsById(id)) {
      throw new ResourceNotFoundException("Customer not found with id: " + id);
    }
    customerRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public Customer findEntityByIdOrThrow(Long id) {
    return customerRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
  }

  private void validateUniqueFields(Customer currentCustomer, CustomerRequest request) {
    List<FieldErrorResponse> errors = new ArrayList<>();

    if ((currentCustomer == null
            || !currentCustomer.getCustomerCode().equals(request.customerCode()))
        && customerRepository.existsByCustomerCode(request.customerCode())) {
      errors.add(new FieldErrorResponse("customerCode", "CUSTOMER_CODE_ALREADY_EXISTS"));
    }

    if ((currentCustomer == null || !currentCustomer.getEmail().equals(request.email()))
        && customerRepository.existsByEmail(request.email())) {
      errors.add(new FieldErrorResponse("email", "EMAIL_ALREADY_EXISTS"));
    }

    if ((currentCustomer == null
            || !currentCustomer.getBusinessName().equals(request.businessName()))
        && customerRepository.existsByBusinessName(request.businessName())) {
      errors.add(new FieldErrorResponse("businessName", "BUSINESS_NAME_ALREADY_EXISTS"));
    }

    if (!errors.isEmpty()) {
      throw new ValidationException(errors);
    }
  }
}
