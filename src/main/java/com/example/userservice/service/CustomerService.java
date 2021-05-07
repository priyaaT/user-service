package com.example.userservice.service;

import com.example.userservice.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getUsers();

    Customer getUserById(Integer id);

    Customer insert(Customer customer);

    Customer updateUser(Integer id, Customer customer);

    void deleteUser(Integer id);
}
