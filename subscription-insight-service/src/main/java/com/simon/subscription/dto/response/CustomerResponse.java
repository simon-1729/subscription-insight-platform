package com.simon.subscription.dto.response;

import java.util.UUID;

public record CustomerResponse(
    UUID id,
    String firstName,
    String lastName
){}