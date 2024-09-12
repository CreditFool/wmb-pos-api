package com.creditfool.warung_makan_bahari.controller.api.v1;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.creditfool.warung_makan_bahari.dto.request.CustomerCreateOrUpdateRequest;
import com.creditfool.warung_makan_bahari.dto.response.CommonResponse;
import com.creditfool.warung_makan_bahari.dto.response.CommonResponseWithPaging;
import com.creditfool.warung_makan_bahari.dto.response.CustomerListResponse;
import com.creditfool.warung_makan_bahari.entity.Customer;
import com.creditfool.warung_makan_bahari.service.CustomerService;
import com.creditfool.warung_makan_bahari.util.ResponseMakerUtil;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("")
    public ResponseEntity<CommonResponseWithPaging<List<CustomerListResponse>>> getAllCustomer(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size

    ) {
        Page<CustomerListResponse> data = customerService.getAllCustomer(page, size);
        return ResponseMakerUtil.createWithPaging(
                HttpStatus.OK,
                "All customer fetched",
                data,
                page,
                size);
    }

    @GetMapping("{id}")
    public ResponseEntity<CommonResponse<Customer>> getCustomerById(
            @PathVariable UUID id

    ) {
        return ResponseMakerUtil.create(
                HttpStatus.OK, "Data found", customerService.getCustomerById(id));
    }

    @PostMapping("")
    public ResponseEntity<CommonResponse<Customer>> createCustomer(
            @Valid @RequestBody CustomerCreateOrUpdateRequest request

    ) {
        return ResponseMakerUtil.create(
                HttpStatus.CREATED, "Customer created", customerService.createCustomer(request));
    }

    @PutMapping("{id}")
    public Customer updateOrCreateCustomer(
            @PathVariable UUID id,
            @Valid @RequestBody CustomerCreateOrUpdateRequest request

    ) {
        return customerService.updateOrCreateCustomer(id, request);
    }

    @PatchMapping("{id}")
    public ResponseEntity<CommonResponse<Customer>> updateCustomer(
            @PathVariable UUID id,
            @RequestBody CustomerCreateOrUpdateRequest request

    ) {
        return ResponseMakerUtil.create(
                HttpStatus.OK, "Customer updated", customerService.updateCustomerById(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CommonResponse<Object>> deleteCustomer(@PathVariable UUID id) {
        customerService.deleteCustomer(id);
        return ResponseMakerUtil.create(HttpStatus.OK, "Data deleted");
    }
}
