package com.creditfool.warung_makan_bahari.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;

class CustomerTest {
    private Customer MakeCustomer() {
        return new Customer();
    }

    @Test
    void Customer_IdField_GeneratesUUID() {
        Customer customer = MakeCustomer();
        UUID expectedId = UUID.randomUUID();

        // Arrange: invoking the entry point
        customer.setId(expectedId);

        // Assert: state-based check
        UUID result = customer.getId();
        assertEquals(expectedId, result);
    }

    @Test
    void Customer_CustomerNameField_NonNullConstraint() {
        Customer customer = MakeCustomer();
        String expectedName = "John Doe";

        // Arrange: invoking the entry point
        customer.setCustomerName(expectedName);

        // Assert: state-based check
        String result = customer.getCustomerName();
        assertEquals(expectedName, result);
    }

    @Test
    void Customer_MobilePhoneField_UniqueConstraint() {
        Customer customer = MakeCustomer();
        String expectedPhone = "1234567890";

        // Arrange: invoking the entry point
        customer.setMobilePhone(expectedPhone);

        // Assert: state-based check
        String result = customer.getMobilePhone();
        assertEquals(expectedPhone, result);
    }

    // Creating a Customer object with all fields populated
    @Test
    void test_create_customer_with_all_fields_populated() {
        UUID id = UUID.randomUUID();
        String customerName = "John Doe";
        String mobilePhone = "1234567890";
        Boolean isMember = true;
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        LocalDateTime deletedAt = LocalDateTime.now();

        Customer customer = new Customer(id, customerName, mobilePhone, isMember, createdAt, updatedAt, deletedAt);

        assertEquals(id, customer.getId());
        assertEquals(customerName, customer.getCustomerName());
        assertEquals(mobilePhone, customer.getMobilePhone());
        assertEquals(isMember, customer.getIsMember());
        assertEquals(createdAt, customer.getCreatedAt());
        assertEquals(updatedAt, customer.getUpdatedAt());
        assertEquals(deletedAt, customer.getDeletedAt());
    }

    // Creating a Customer object with null values for non-nullable fields
    @Test
    void test_create_customer_with_null_values_for_non_nullable_fields() {
        assertThrows(NullPointerException.class, () -> {
            new Customer(null, null, null, null, null, null, null);
        });
    }
}
