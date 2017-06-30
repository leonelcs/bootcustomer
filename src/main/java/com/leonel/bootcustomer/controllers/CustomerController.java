package com.leonel.bootcustomer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.leonel.bootcustomer.models.Customer;
import com.leonel.bootcustomer.registries.CustomerRegistry;

@RestController
public class CustomerController {

    @Autowired
    CustomerRegistry customerRegistry;
    
    @RequestMapping( path="/register", method = RequestMethod.POST)
    Customer register(@RequestBody Customer customer){
      return customerRegistry.register(customer);
    }
 
}
