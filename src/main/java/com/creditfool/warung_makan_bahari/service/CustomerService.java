package com.creditfool.warung_makan_bahari.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.creditfool.warung_makan_bahari.dto.request.CustomerCreateOrUpdateRequest;
import com.creditfool.warung_makan_bahari.dto.response.CustomerListResponse;
import com.creditfool.warung_makan_bahari.entity.Customer;

@Service
public interface CustomerService {

    public List<CustomerListResponse> getAllCustomer();

    public Page<CustomerListResponse> getAllCustomer(Integer page, Integer size);

    public Customer getCustomerById(UUID id);

    public Customer createCustomer(CustomerCreateOrUpdateRequest request);

    public Customer updateOrCreateCustomer(UUID id, CustomerCreateOrUpdateRequest request);

    public Customer updateCustomerById(UUID id, CustomerCreateOrUpdateRequest request);

    public void deleteCustomer(UUID id);

}
