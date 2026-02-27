package com.simon.subscription.dto.response;

import java.util.UUID;

import com.simon.subscription.domain.PlanType;
import com.simon.subscription.domain.SubscriptionStatus;

public record SubscriptionResponse(
    UUID id,
    UUID customerId,
    PlanType planType,
    SubscriptionStatus status
) {}
