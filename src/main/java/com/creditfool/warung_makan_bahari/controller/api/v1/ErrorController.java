package com.creditfool.warung_makan_bahari.controller.api.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.creditfool.warung_makan_bahari.dto.response.CommonResponse;
import com.creditfool.warung_makan_bahari.util.ResponseMakerUtil;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<CommonResponse<Object>> responseStatusException(ResponseStatusException e) {
        return ResponseMakerUtil.create(e.getStatusCode(), e.getReason(), null);
    }
}
