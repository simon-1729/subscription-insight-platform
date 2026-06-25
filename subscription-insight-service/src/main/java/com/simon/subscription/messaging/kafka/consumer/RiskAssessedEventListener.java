package com.simon.subscription.messaging.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.simon.subscription.messaging.events.RiskAssessedEvent;
import com.simon.subscription.service.RiskAssessedEventService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class RiskAssessedEventListener {

    private final RiskAssessedEventService riskAssessedEventService;

    @KafkaListener(
        topics = "risk-assessed-topic",
        groupId = "subscription-group",
        properties = {
            "spring.json.value.default.type=com.simon.subscription.messaging.events.RiskAssessedEvent"
        }
    )
    public void listen(RiskAssessedEvent event) {
        log.info("RiskAssessedEventListener: received event: {}", event);
        riskAssessedEventService.process(event);
    }
}