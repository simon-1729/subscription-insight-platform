package com.simon.subscription.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simon.subscription.domain.Customer;


public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
