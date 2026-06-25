package com.simon.subscription.messaging.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.simon.subscription.messaging.events.UsageEvent;
import com.simon.subscription.service.UsageEventService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UsageEventListener {

    private final UsageEventService usageEventService;

    @KafkaListener(
        topics = "usage-topic",
        groupId = "subscription-group",
        properties = {"spring.json.value.default.type=com.simon.subscription.messaging.events.UsageEvent"}
    )
    public void listen(UsageEvent event) {

        usageEventService.processUsageEvent(event);
    }
}
