package com.makaia.Hotel.controllers;

import com.makaia.Hotel.dto.CustomerDTO;
import com.makaia.Hotel.modules.Customer;
import com.makaia.Hotel.modules.Room;
import com.makaia.Hotel.services.CustomerService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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
    @ApiResponses(value = {
            @ApiResponse(code = 200, message ="Everything is Ok"),
            @ApiResponse(code = 404, message ="That's an error in the client service"),
            @ApiResponse(code = 500, message ="That's an internal error"),
    })
    @ApiOperation(value="List's customers", notes= "this searches all customers", response = CustomerDTO.class)
    @GetMapping("/customers")
    public List<CustomerDTO> getCustomers(){
        return this.customerService.getCustomers();
    }
    @ApiResponses(value = {
            @ApiResponse(code = 200, message ="Everything is Ok"),
            @ApiResponse(code = 404, message ="That's an error in the client service"),
            @ApiResponse(code = 500, message ="That's an internal error"),
    })
    @ApiOperation(value="customer object", notes= "this searches a customer by dni", response = CustomerDTO.class)
    @GetMapping("/customer/{dni}")
    public Optional<CustomerDTO> getCustomerDNI(@PathVariable("dni") int dni){
        return this.customerService.getCustomer(dni);
    }


}