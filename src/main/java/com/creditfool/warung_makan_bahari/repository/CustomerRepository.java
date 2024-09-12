package com.creditfool.warung_makan_bahari.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.creditfool.warung_makan_bahari.dto.response.CustomerListResponse;
import com.creditfool.warung_makan_bahari.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findByIdAndDeletedAtIsNull(UUID id);

    List<Customer> findAllByDeletedAtIsNull();

    Page<CustomerListResponse> findAllByDeletedAtIsNull(Pageable pageable);

    Optional<Customer> findByMobilePhoneAndDeletedAtIsNull(String mobilePhone);
}
