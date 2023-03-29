package com.makaia.Hotel.modules;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
@ApiModel(description ="this model represent the reservation data")
@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {
    @ApiModelProperty(value = "reservation id", example ="1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reserveCode;
    @ApiModelProperty(value = "reservation date", example ="1")
    @Column(name = "reserveDate")
    private LocalDate reserveDate;
    @ApiModelProperty(value = "reservation total value", example ="1")
    @Column(name = "totalValue")
    private Double totalValue;
    @ApiModelProperty(value = "customer", example ="1")
    @ManyToOne
    @JoinColumn(name = "dni")
    @JsonIgnoreProperties("reservations")
    private Customer customer;
    @ApiModelProperty(value = "room", example ="1")
    @ManyToOne
    @JoinColumn(name = "numberRoom")
    @JsonIgnoreProperties("reservations")
    private Room room;

    public Reservation(){}
    public Reservation(Integer reserveCode, LocalDate reserveDate, Double totalValue, Customer customer, Room room) {
        this.reserveCode = reserveCode;
        this.reserveDate = reserveDate;
        this.totalValue = totalValue;
        this.customer = customer;
        this.room = room;
    }

    private String generarCodigoReserva() {
        return UUID.randomUUID().toString();
    }

    public Integer getReserveCode() {
        return reserveCode;
    }

    public void setReserveCode(Integer reserveCode) {
        this.reserveCode = reserveCode;
    }

    public LocalDate getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(LocalDate reserveDate) {
        this.reserveDate = reserveDate;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}