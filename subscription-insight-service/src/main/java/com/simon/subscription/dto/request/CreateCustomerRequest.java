package com.simon.subscription.dto.request;

import jakarta.validation.constraints.NotNull;

public record CreateCustomerRequest(
    @NotNull String email,
    @NotNull String firstName,
    @NotNull String lastName
) {}