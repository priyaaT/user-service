package controller;

import com.example.userservice.controller.CustomerController;
import com.example.userservice.model.AccountType;
import com.example.userservice.model.Customer;
import com.example.userservice.repository.CustomerRepository;
import com.example.userservice.service.CustomerService;
import com.example.userservice.service.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class CustomerControllerTest {

    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    private CustomerRepository customerRepository;

    @Before
    public void setUp() {
        customerService = mock(CustomerServiceImpl.class);
        customerController = new CustomerController(customerService);
        customerRepository = mock(CustomerRepository.class);
    }

    @Test
    public void testSaveCustomerDetails() {
        Customer customer = Customer.builder().accountType(AccountType.CURRENT).id(6)
                .email("priyavms@gmail.com").build();
        when(customerService.insert(customer)).thenReturn(customer);
        ResponseEntity<Customer> responseEntity = customerController.saveCustomerDetails(customer);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
    }

    @Test
    public void testFetchDetails() {
        Customer customer = Customer.builder().accountType(AccountType.CURRENT).id(6)
                .email("priyavms@gmail.com").build();
        when(customerService.getUserById(6)).thenReturn(customer);
        ResponseEntity<Customer> responseEntity = customerController.loginCustomer(6);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(202);
    }

    @Test
    public void testUpdateDetails() {
        Customer customer = Customer.builder().accountType(AccountType.CURRENT).id(6)
                .email("priyavms@gmail.com").build();
        when(customerService.updateUser(6,customer)).thenReturn(customer);
        ResponseEntity<Customer> responseEntity = customerController.updateUser(6,customer);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void testDeleteDetails() {
        ResponseEntity<Customer> responseEntity = customerController.deleteUser(6);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(204);
    }
}
