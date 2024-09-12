package com.creditfool.warung_makan_bahari.dto.response;

public record PagingResponse(
        int totalPages,
        int count,
        Integer page,
        Integer size) {
}
