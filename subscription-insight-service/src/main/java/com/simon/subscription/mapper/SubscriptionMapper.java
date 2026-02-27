package com.simon.subscription.mapper;

import org.springframework.stereotype.Component;

import com.simon.subscription.domain.Subscription;
import com.simon.subscription.dto.response.SubscriptionResponse;

@Component
public class SubscriptionMapper {

    public SubscriptionResponse mapperToResponse(Subscription subscription) { 
        return new SubscriptionResponse( 
            subscription.getId(), 
            subscription.getCustomer().getId(), 
            subscription.getPlanType(), 
            subscription.getStatus()); 
        }

}
