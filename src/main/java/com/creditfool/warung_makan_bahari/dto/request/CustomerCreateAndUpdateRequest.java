package com.creditfool.warung_makan_bahari.dto.request;

import com.creditfool.warung_makan_bahari.entity.Customer;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;

public record CustomerCreateAndUpdateRequest(
        @NotBlank String customerName,
        @NotBlank String mobilePhone,
        @NonNull Boolean isMember) {

    public Customer toCustomer() {
        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        customer.setMobilePhone(mobilePhone);
        customer.getIsMember();
        return customer;
    }
}
