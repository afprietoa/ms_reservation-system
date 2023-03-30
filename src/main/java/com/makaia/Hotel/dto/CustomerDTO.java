package com.makaia.Hotel.dto;

public class CustomerDTO {
    private Integer dni;
    private String firstName;

    private String lastName;

    public CustomerDTO(Integer dni, String firstName, String lastName) {
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public CustomerDTO() {
    }

    public Integer getDni() {
        return dni;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}