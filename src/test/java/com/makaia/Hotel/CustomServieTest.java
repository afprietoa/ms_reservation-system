package com.makaia.Hotel;

import com.makaia.Hotel.modules.Customer;
import com.makaia.Hotel.repositories.CustomerRepository;
import com.makaia.Hotel.services.CustomerService;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class CustomServieTest {
    CustomerRepository customerRepository;
    CustomerService customerService;

    @Before
    public void setUp(){
        this.customerRepository = mock(CustomerRepository.class);
        this.customerService = new CustomerService(customerRepository);
    }

    @Test
    public void validateCustomer() {
        Customer customer = new Customer(1, "Pepito", "Peréz", "Calle falsa 123", 18, "peto@example.com");
        Customer custom = customerService.create(customer);

        assertTrue((custom.getFirstName() != null && custom.getLastName() != null && custom.getDni() instanceof Integer));
    }


}
