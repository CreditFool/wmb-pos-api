package com.creditfool.warung_makan_bahari.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import com.creditfool.warung_makan_bahari.dto.request.CustomerCreateOrUpdateRequest;
import com.creditfool.warung_makan_bahari.dto.response.CustomerListResponse;
import com.creditfool.warung_makan_bahari.entity.Customer;
import com.creditfool.warung_makan_bahari.exception.DataAlreadyExistException;
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
    public Customer createCustomer(CustomerCreateOrUpdateRequest request) {
        Customer customer = request.toCustomer();
        Optional<Customer> savedCustomer = customerRepository
                .findByMobilePhoneAndDeletedAtIsNull(request.mobilePhone());
        if (savedCustomer.isPresent()) {
            throw new DataAlreadyExistException("Customer with same mobile phone number already exists");
        }
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(UUID id) {
        Optional<Customer> customer = customerRepository.findByIdAndDeletedAtIsNull(id);
        if (customer.isEmpty()) {
            throw customerNotFoundException;
        }
        customer.get().setDeletedAt(LocalDateTime.now());
        customerRepository.save(customer.get());
    }

    @Override
    public List<CustomerListResponse> getAllCustomer() {
        List<Customer> activeCustomers = customerRepository.findAllByDeletedAtIsNull();
        return activeCustomers.stream().map(customer -> new CustomerListResponse(
                customer.getId(), customer.getCustomerName(), customer.getMobilePhone(), customer.getIsMember()))
                .toList();
    }

    @Override
    public Page<CustomerListResponse> getAllCustomer(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return customerRepository.findAllByDeletedAtIsNull(pageable);
    }

    @Override
    public Customer getCustomerById(UUID id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    public Customer updateCustomer(Customer savedCustomer, Customer updatedCustomer) {
        if (updatedCustomer.getCustomerName() != null) {
            savedCustomer.setCustomerName(updatedCustomer.getCustomerName());
        }
        if (updatedCustomer.getMobilePhone() != null) {
            savedCustomer.setMobilePhone(updatedCustomer.getMobilePhone());
        }
        if (updatedCustomer.getIsMember() != null) {
            savedCustomer.setIsMember(updatedCustomer.getIsMember());
        }
        savedCustomer.setUpdatedAt(LocalDateTime.now());
        return customerRepository.save(savedCustomer);
    }

    @Override
    public Customer updateCustomerById(UUID id, CustomerCreateOrUpdateRequest request) {
        Customer savedCustomer = customerRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        return updateCustomer(savedCustomer, request.toCustomer());
    }

    @Override
    public Customer updateOrCreateCustomer(UUID id, CustomerCreateOrUpdateRequest request) {
        Optional<Customer> savedCustomer = customerRepository.findByIdAndDeletedAtIsNull(id);
        if (savedCustomer.isPresent()) {
            return updateCustomer(savedCustomer.get(), request.toCustomer());
        }
        Customer newCustomer = createCustomer(request);
        newCustomer.setId(id);
        return customerRepository.save(newCustomer);
    }

}
