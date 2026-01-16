package com.tiba.backend.customer.service;

import com.tiba.backend.customer.dto.CustomerRequest;
import com.tiba.backend.customer.dto.CustomerResponse;
import com.tiba.backend.shared.PageResponse;

import java.util.List;

public interface CustomerService {

  Long create(CustomerRequest request);

  CustomerResponse getById(Long id);

  List<CustomerResponse> getAll();

  PageResponse<CustomerResponse> getAllWithPagination(int page, int size);

  CustomerResponse update(Long id, CustomerRequest request);

  void delete(Long id);
}
