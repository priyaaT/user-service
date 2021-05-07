package com.example.userservice.controller;

import com.example.userservice.model.Customer;
import com.example.userservice.service.CustomerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/personalDetails")
public class CustomerController {

    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/save")
    public ResponseEntity<Customer> saveCustomerDetails(@Valid @RequestBody Customer newCustomer) {
        Customer customerDetails1 = customerService.insert(newCustomer);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("personalDetails", "/api/v1/personalDetails/");
        return new ResponseEntity<>(customerDetails1, httpHeaders, HttpStatus.CREATED);
    }

    @GetMapping("/login/{id}")
    public ResponseEntity<Customer> loginCustomer(@PathVariable("id") Integer userId) {
        Customer customerDetails = customerService.getUserById(userId);
        if (customerDetails != null) {
            return new ResponseEntity<>(customerDetails, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping({"/update/{id}"})
    public ResponseEntity<Customer> updateUser(@PathVariable("id") Integer userId, @RequestBody Customer customerDetails) {
        customerService.updateUser(userId, customerDetails);
        return new ResponseEntity<>(customerService.getUserById(userId), HttpStatus.OK);
    }

    @DeleteMapping({"/delete/{id}"})
    public ResponseEntity<Customer> deleteUser(@PathVariable("id") Integer userId) {
        customerService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


