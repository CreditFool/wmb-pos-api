package com.creditfool.warung_makan_bahari.dto.customer;

import org.hibernate.validator.constraints.UUID;

public record CustomerListDto(
        UUID id,
        String customerName) {
}
