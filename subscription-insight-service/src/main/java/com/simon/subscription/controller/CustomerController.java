package com.simon.subscription.controller;


import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simon.subscription.dto.request.CreateCustomerRequest;
import com.simon.subscription.dto.response.CustomerResponse;
import com.simon.subscription.service.CustomerService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @PostMapping
    public CustomerResponse createCustomer(@RequestBody CreateCustomerRequest request) {

        return customerService.createCustomer(
            request.email(),
            request.firstName(),
            request.lastName()
        );
    }

    @GetMapping
    public CustomerResponse getCustomer(@RequestParam UUID id) {
        return customerService.getCustomer(id);
    }

}
