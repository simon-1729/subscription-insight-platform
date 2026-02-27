package com.simon.subscription.messaging.events;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsageEvent {

    private UUID eventId;
    private UUID subscriptionId;
    private EventType eventType;
    private Instant timestamp;
    private String details;    
}