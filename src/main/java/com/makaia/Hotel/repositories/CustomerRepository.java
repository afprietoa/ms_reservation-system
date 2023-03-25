package com.makaia.Hotel.repositories;

import com.makaia.Hotel.modules.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}