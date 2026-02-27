package com.simon.subscription.service;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.simon.subscription.domain.Customer;
import com.simon.subscription.domain.PlanType;
import com.simon.subscription.domain.Subscription;
import com.simon.subscription.domain.SubscriptionStatus;
import com.simon.subscription.dto.response.SubscriptionResponse;
import com.simon.subscription.mapper.SubscriptionMapper;
import com.simon.subscription.repository.CustomerRepository;
import com.simon.subscription.repository.SubscriptionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final CustomerRepository customerRepository;
    private final SubscriptionMapper subscriptionMapper;

    public SubscriptionResponse createSubscription(UUID customerId, PlanType planType) {
        // Fetch the customer from the database
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Create a new subscription
        Subscription subscription = Subscription.builder()
                .customer(customer)
                .planType(planType)
                .status(SubscriptionStatus.ACTIVE)
                .startDate(LocalDate.now())
                .renewalDate(LocalDate.now().plusMonths(1))
                .build();

        // Save the subscription to the database
       subscriptionRepository.save(subscription);

       // Return DTO response
       return subscriptionMapper.mapperToResponse(subscription);
    }

}
