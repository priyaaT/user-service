package com.example.userservice.service;

import com.example.userservice.model.AccountType;
import com.example.userservice.model.Customer;
import com.example.userservice.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.List;

import static java.util.Optional.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class CustomerServiceTest {


    @Mock
    private CustomerService customerService;

    private CustomerRepository customerRepository;

    @Before
    public void setUp() {
        customerRepository = mock(CustomerRepository.class);
        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    public void testSaveLoanDetails() {
        Customer customer = Customer.builder().userName("priya").accountType(AccountType.CURRENT).id(6).build();
        when(customerRepository.save(customer)).thenReturn(customer);
        Customer customer1 = customerService.insert(customer);
        assertThat(customer.equals(customer1));
    }

    @Test
    public void testFetchCustomerDetails() {
        Customer customer = Customer.builder().userName("priya").accountType(AccountType.CURRENT).id(6).build();
        when(customerRepository.findById(6)).thenReturn(ofNullable(customer));
        Customer customer2 = customerService.getUserById(6);
        assertThat(customer2.getId() == 6);
    }

    @Test
    public void testUpdateCustomerDetails() {
        Customer customer = Customer.builder().userName("priya").accountType(AccountType.CURRENT).id(6).build();
        Customer customer1 = Customer.builder().userName("prabha").accountType(AccountType.CURRENT).id(6).build();
        when(customerRepository.findById(6)).thenReturn(ofNullable(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer1);
        Customer customer2 = customerService.updateUser(6,customer1);
        assertThat(customer2.equals(customer1));
    }
}
