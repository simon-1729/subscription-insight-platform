package com.simon.subscription.service;

import org.springframework.stereotype.Service;

import com.simon.subscription.messaging.events.RiskAssessedEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RiskAssessedEventService {

    public void process(RiskAssessedEvent event) {
        log.info("RiskAssessedEventService: processing risk assessment");
        log.info("  Subscription ID : {}", event.getOriginal().getSubscriptionId());
        log.info("  Event Type      : {}", event.getOriginal().getEventType());
        log.info("  Timestamp       : {}", event.getOriginal().getTimestamp());
        log.info("  Risk Score      : {}", event.getRiskScore());
        log.info("  Risk Level      : {}", event.getRiskLevel());
    }
}