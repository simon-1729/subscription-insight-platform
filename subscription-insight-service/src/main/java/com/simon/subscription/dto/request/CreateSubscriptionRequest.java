package com.simon.subscription.dto.request;

import java.util.UUID;

import com.simon.subscription.domain.PlanType;

import jakarta.validation.constraints.NotNull;

public record CreateSubscriptionRequest(
    @NotNull UUID customerId, 
    @NotNull PlanType planType) {
}
