package com.creditfool.warung_makan_bahari.dto.response;

import java.util.UUID;

public record CustomerListResponse(
        UUID id,
        String customerName,
        String mobilePhone,
        Boolean isMember) {
}
