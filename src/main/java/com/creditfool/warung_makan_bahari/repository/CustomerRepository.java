package com.creditfool.warung_makan_bahari.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.creditfool.warung_makan_bahari.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
