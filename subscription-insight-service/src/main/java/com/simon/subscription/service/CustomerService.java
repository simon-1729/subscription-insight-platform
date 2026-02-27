package com.simon.subscription.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.simon.subscription.domain.Customer;
import com.simon.subscription.dto.response.CustomerResponse;
import com.simon.subscription.mapper.CustomerMapper;
import com.simon.subscription.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    
    public CustomerResponse createCustomer(String email, String firstName, String lastName) {
        Customer customer = Customer.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .createdAt(LocalDateTime.now())
                .build();
                
        customerRepository.save(customer);

        return customerMapper.mapperToResponse(customer);
    }

    public CustomerResponse getCustomer(UUID id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

                return customerMapper.mapperToResponse(customer);
    }

}
