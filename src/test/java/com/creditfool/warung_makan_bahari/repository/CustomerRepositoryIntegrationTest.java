package com.creditfool.warung_makan_bahari.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.creditfool.warung_makan_bahari.entity.Customer;

@SpringBootTest
class CustomerRepositoryIntegrationTest {

    @Autowired
    CustomerRepository repository;

    @BeforeEach
    void setup() {
        Customer cus1 = new Customer();
        cus1.setCustomerName("Leila");
        cus1.setMobilePhone("1234567890");
        cus1.setIsMember(true);

        Customer cus2 = new Customer();
        cus2.setCustomerName("Kokoro");
        cus2.setMobilePhone("1234567891");
        cus2.setIsMember(false);

        Customer cus3 = new Customer();
        cus3.setCustomerName("Bulqi");
        cus3.setMobilePhone("085159550808");
        cus3.setIsMember(true);
        cus3.setDeletedAt(LocalDateTime.now());

        repository.saveAll(List.of(cus1, cus2, cus3));
    }

    @AfterEach
    void clear() {
        repository.deleteAll();
    }

    @Test
    void testFindAllByDeletedAtIsNull() {
        List<Customer> customersList = repository.findAllByDeletedAtIsNull();
        assertEquals(2, customersList.size());
    }

    @Test
    void testFindByByMobilePhoneAndDeletedAtIsNull() {
        Optional<Customer> customerActive = repository.findByMobilePhoneAndDeletedAtIsNull(
                "1234567890");

        Optional<Customer> customerNotActive = repository.findByMobilePhoneAndDeletedAtIsNull(
                "085159550808");

        assertTrue(customerActive.isPresent());
        assertFalse(customerNotActive.isPresent());
    }
}
