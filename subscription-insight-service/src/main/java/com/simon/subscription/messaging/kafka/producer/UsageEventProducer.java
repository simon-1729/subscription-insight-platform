package com.simon.subscription.messaging.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.simon.subscription.messaging.events.Topics;
import com.simon.subscription.messaging.events.UsageEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsageEventProducer {

   private final KafkaTemplate<String, UsageEvent> kafkaTemplate;

    public void sendUsageEvent(UsageEvent event) {
    
        kafkaTemplate.send(Topics.USAGE_TOPIC.getTopicName(), event)
            .whenComplete((result, ex) -> {
                if (ex != null) {
                    log.error("Failed to send usage event: {}", event, ex);
                } else {
                    log.info("EVENT sent to partition {}", 
                        result.getRecordMetadata().partition());
                }
            });        
            log.info("Logging: Sent usage event: {}", event);
    }   

}
