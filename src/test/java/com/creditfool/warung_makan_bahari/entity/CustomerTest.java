package com.creditfool.warung_makan_bahari.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;

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
}
