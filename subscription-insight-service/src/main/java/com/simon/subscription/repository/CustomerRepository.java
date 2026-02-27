package com.simon.subscription.repository;

import java.util.UUID;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simon.subscription.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID>{
}
