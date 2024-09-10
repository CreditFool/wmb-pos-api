package com.creditfool.warung_makan_bahari.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.creditfool.warung_makan_bahari.dto.request.CustomerCreateAndUpdateRequest;
import com.creditfool.warung_makan_bahari.entity.Customer;
import com.creditfool.warung_makan_bahari.exception.NotFoundException;
import com.creditfool.warung_makan_bahari.repository.CustomerRepository;
import com.creditfool.warung_makan_bahari.service.CustomerService;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final NotFoundException customerNotFoundException = new NotFoundException("Customer Not Found");

    @Override
    public Customer createCustomer(CustomerCreateAndUpdateRequest request) {
        Customer customer = request.toCustomer();
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(UUID id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw customerNotFoundException;
        }
        customerRepository.delete(customer.get());
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(UUID id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw customerNotFoundException;
        }
        return customer.get();
    }

    @Override
    public Customer updateCustomer(UUID id, CustomerCreateAndUpdateRequest cur) {
        Optional<Customer> savedCustomer = customerRepository.findById(id);
        if (savedCustomer.isEmpty()) {
            throw customerNotFoundException;
        }
        savedCustomer.get().setCustomerName(cur.customerName());
        savedCustomer.get().setMobilePhone(cur.mobilePhone());
        savedCustomer.get().setIsMember(cur.isMember());
        return customerRepository.save(savedCustomer.get());
    }

}