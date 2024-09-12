package com.creditfool.warung_makan_bahari.dto.response;

public record CommonResponseWithPaging<T>(
        Integer statusCode,
        String message,
        T data,
        PagingResponse paging) {
}
