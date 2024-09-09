package com.creditfool.warung_makan_bahari.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.creditfool.warung_makan_bahari.dto.customer.CustomerCreateUpdateDto;
import com.creditfool.warung_makan_bahari.dto.customer.CustomerListDto;
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
    public Customer createCustomer(CustomerCreateUpdateDto ccd) {
        Customer customer = new Customer();
        customer.setCustomerName(ccd.customerName());
        customer.setMobilePhone(ccd.mobilePhone());
        customer.setIsMember(ccd.isMember());
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
    public Customer updateCustomer(UUID id, Customer customer) {
        Optional<Customer> oldCustomer = customerRepository.findById(id);
        if (oldCustomer.isEmpty()) {
            throw customerNotFoundException;
        }
        customer.setId(id);
        return customerRepository.save(customer);
    }

}