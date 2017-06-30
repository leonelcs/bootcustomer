package com.leonel.bootcustomer.registries;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.leonel.bootcustomer.models.Customer;
import com.leonel.bootcustomer.queue.Sender;
import com.leonel.bootcustomer.repositories.CustomerRepository;

@Component
@Lazy
public class CustomerRegistry {

	CustomerRepository customerRepository;
	
	Sender sender;
	
	@Autowired
	public CustomerRegistry(CustomerRepository customerRespository, Sender sender) {
	    this.customerRepository = customerRespository;
	    this.sender = sender;
	}
	
	public Customer register(Customer customer) {

		Optional<Customer> existingCustomer = customerRepository.findByName(customer.getName());
		if (existingCustomer.isPresent()) {
			throw new RuntimeException("Customer already exists");
		} else {
			customerRepository.save(customer);
			sender.send(customer.getEmail());
		}
		return customer;
	}


}
