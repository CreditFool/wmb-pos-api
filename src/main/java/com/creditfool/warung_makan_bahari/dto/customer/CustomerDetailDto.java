package com.creditfool.warung_makan_bahari.dto.customer;

import org.hibernate.validator.constraints.UUID;

public record CustomerDetailDto(
        UUID id,
        String customerName,
        String mobilePhone,
        Boolean isMember,
        Boolean isActive) {
}
