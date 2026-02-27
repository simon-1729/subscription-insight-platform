package com.simon.subscription.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simon.subscription.domain.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {

}
