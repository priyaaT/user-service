package com.example.userservice.service;

import com.example.userservice.exception.NoDataFoundException;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.model.Customer;
import com.example.userservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getUsers() {
        List<Customer> customerDetails = new ArrayList<>();
        customerRepository.findAll().forEach(customerDetails::add);
        if (customerDetails.isEmpty()) {
            throw new NoDataFoundException();
        }
        return customerDetails;
    }

    @Override
    public Customer getUserById(Integer id) {
        return customerRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public Customer insert(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void updateUser(Integer id, Customer customer) {
        Customer customerFromDb = customerRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        customerFromDb.setAddress(customer.getAddress());
        customerFromDb.setName(customer.getName());
        customerFromDb.setPan(customer.getPan());
        customerRepository.save(customerFromDb);
    }

    @Override
    public void deleteUser(Integer id) {
        customerRepository.deleteById(id);
    }
}