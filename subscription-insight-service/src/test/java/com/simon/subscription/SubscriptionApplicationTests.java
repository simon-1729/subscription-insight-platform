package com.simon.subscription;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.simon.subscription.domain.Customer;
import com.simon.subscription.domain.PlanType;
import com.simon.subscription.domain.Subscription;
import com.simon.subscription.domain.SubscriptionStatus;
import com.simon.subscription.repository.CustomerRepository;
import com.simon.subscription.repository.SubscriptionRepository;

@SpringBootTest
@ActiveProfiles("test")
class SubscriptionApplicationTests {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private SubscriptionRepository subscriptionRepository;

	private Customer customer;

	@Test
	void contextLoads() {
	}

	@BeforeEach
    void setUp() {
        customer = Customer.builder()
			.email("testuser@email.com")
			.firstName("test")
			.lastName("user")
			.createdAt(LocalDateTime.now())
			.build();
    }

	@Test
	void shouldPersistCustomerDetailslWhenSaving() {
		UUID customerId = customerRepository.save(customer).getId();
		Customer customerFromDB = customerRepository.findById(customerId).orElseThrow();

		assertEquals("testuser@email.com", customerFromDB.getEmail());
		assertEquals("test", customerFromDB.getFirstName());
		assertEquals("user", customerFromDB.getLastName());
	}

	@Test
	void shouldPersistSubscriptionForCustomer() {
		Customer savedCustomer = customerRepository.save(customer);

		Subscription subscription = Subscription.builder()
			.customer(savedCustomer)
			.planType(PlanType.BASIC)
			.status(SubscriptionStatus.ACTIVE)
			.startDate(LocalDate.now())
			.renewalDate(LocalDate.now().plusMonths(1))
			.build();

		UUID subscriptionID = subscriptionRepository.save(subscription).getId();
		Subscription subscriptionFromDB = subscriptionRepository.findById(subscriptionID).orElseThrow();

		assertEquals(savedCustomer.getId() , subscriptionFromDB.getCustomer().getId());
	}

}
