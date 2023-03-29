package com.makaia.Hotel.modules;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@ApiModel(description ="this model represent the customer data")
@Entity
@Table(name = "customer")
public class Customer implements Serializable {
    @ApiModelProperty(value = "customer id", example ="1")
    @Id
    private Integer dni;
    @ApiModelProperty(value = "customer first name", example ="Pepito")
    @Column(name = "firstName", length = 50)
    private String firstName;
    @ApiModelProperty(value = "customer last name", example ="Perez")
    @Column(name = "lastName", length = 50)
    private String lastName;
    @ApiModelProperty(value = "customer address", example ="Cl falsa 123")
    @Column(name = "address", length = 100)
    private String address;
    @ApiModelProperty(value = "customer age", example ="18")
    @Column(name = "age")
    private Integer age;
    @ApiModelProperty(value = "customer e-mail", example ="1")
    @Column(name = "email")
    private String email;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "customer")
    @JsonIgnoreProperties("customer")
    private List<Reservation> reservations;

    public Customer(){}

    // TODO: delete constructor
    public Customer(Integer dni, String firstName, String lastName, String address, Integer age, String email) {
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.age = age;
        this.email = email;
    }

    public Integer getDni() {
        return dni;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
