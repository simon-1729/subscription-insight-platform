package com.simon.subscription.messaging.events;

public enum Topics {
    USAGE_TOPIC("usage-topic"),
    RISK_ASSESSED_TOPIC("risk-assessed-topic");

    private final String topicName;

    Topics(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }
}
