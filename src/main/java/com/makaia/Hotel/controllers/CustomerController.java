package com.makaia.Hotel.controllers;

import com.makaia.Hotel.modules.Customer;
import com.makaia.Hotel.services.CustomerService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ApiOperation(value="customer", notes= "this create a customer", response = Customer.class)
    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer register(@ApiParam(value = "customer object", required = true) @RequestBody Customer customer){
        return customerService.create(customer);
    }
}