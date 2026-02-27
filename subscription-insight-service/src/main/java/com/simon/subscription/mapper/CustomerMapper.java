package com.simon.subscription.mapper;

import org.springframework.stereotype.Component;

import com.simon.subscription.domain.Customer;
import com.simon.subscription.dto.response.CustomerResponse;

@Component
public class CustomerMapper {

    public CustomerResponse mapperToResponse(Customer customer) { 
        return new CustomerResponse( 
            customer.getId(), 
            customer.getFirstName(), 
            customer.getLastName() ); 
        }

}
