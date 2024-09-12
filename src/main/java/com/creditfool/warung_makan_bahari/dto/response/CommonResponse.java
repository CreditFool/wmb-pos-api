package com.creditfool.warung_makan_bahari.dto.response;

public record CommonResponse<T>(
        Integer statusCode,
        String message,
        T data) {
}
