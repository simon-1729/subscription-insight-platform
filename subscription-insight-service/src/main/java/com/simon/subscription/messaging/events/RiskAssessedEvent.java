package com.simon.subscription.messaging.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiskAssessedEvent {

    @JsonProperty("original")
    private UsageEvent original;

    @JsonProperty("risk_score")
    private double riskScore;

    @JsonProperty("risk_level")
    private String riskLevel;

}
