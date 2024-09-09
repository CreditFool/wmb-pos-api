package com.creditfool.warung_makan_bahari.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.creditfool.warung_makan_bahari.dto.customer.CustomerCreateUpdateDto;
import com.creditfool.warung_makan_bahari.entity.Customer;

@Service
public interface CustomerService {

    public List<Customer> getAllCustomer();

    public Customer getCustomerById(UUID id);

    public Customer createCustomer(CustomerCreateUpdateDto ccd);

    public Customer updateCustomer(UUID id, Customer customer);

    public void deleteCustomer(UUID id);

}
