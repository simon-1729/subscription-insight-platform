package com.simon.subscription.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.simon.subscription.messaging.events.EventType;
import com.simon.subscription.messaging.events.UsageEvent;
import com.simon.subscription.messaging.kafka.producer.UsageEventProducer;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsageEventService {

    private final UsageEventProducer usageEventProducer;

    public void publishMockUsageEvent(UUID subscriptionId) {
        UsageEvent event = UsageEvent.builder()
            .eventId(UUID.randomUUID())
            .subscriptionId(subscriptionId)
            .eventType(EventType.USAGE_REPORTED)
            .timestamp(java.time.Instant.now())
            .details("Mock usage event for subscription ID: " + subscriptionId)
            .build();

        usageEventProducer.sendUsageEvent(event);
    }

    public void processUsageEvent(UsageEvent event) {
        System.out.println("LISTENER: Processing event:  " + event);
    }
}
