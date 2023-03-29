package com.makaia.Hotel.controllers;

import com.makaia.Hotel.modules.Customer;
import com.makaia.Hotel.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
@Api(value="---", description = "This is communication between customer controller and all associative customer services")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @ApiResponses(value={
            @ApiResponse( code = 201, message = "created customer success")
    })
    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer register(@RequestBody Customer customer){
        return customerService.create(customer);
    }
}