package com.simon.subscription.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simon.subscription.dto.request.CreateSubscriptionRequest;
import com.simon.subscription.dto.response.SubscriptionResponse;
import com.simon.subscription.service.SubscriptionService;
import com.simon.subscription.service.UsageEventService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final UsageEventService UsageEventService;

    @PostMapping
    public SubscriptionResponse createSubscription(@RequestBody 
        CreateSubscriptionRequest request) {

        return subscriptionService.createSubscription(
            request.customerId(), 
            request.planType()
        );
    }

    @GetMapping("/mock/{subscriptionId}")
    public void publishMockUsageEvent(@PathVariable UUID subscriptionId) {
        UsageEventService.publishMockUsageEvent(subscriptionId);
    }
}
