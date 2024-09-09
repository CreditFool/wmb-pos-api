package com.creditfool.warung_makan_bahari.dto.customer;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CustomerCreateUpdateDto(
                @NotEmpty String customerName,
                @NotEmpty String mobilePhone,
                @NotNull Boolean isMember) {
}
