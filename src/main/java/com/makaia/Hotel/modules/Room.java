package com.makaia.Hotel.modules;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@ApiModel(description ="this model represent the room data")
@Entity
@Table(name = "room")
public class Room implements Serializable {
    @ApiModelProperty(value = "room id", example ="1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numberRoom;
    @ApiModelProperty(value = "room type", example ="basic")
    @Column(name = "roomType")
    private String roomType;
    @ApiModelProperty(value = "room price", example ="100")
    @Column(name = "price")
    private Double price;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "room")
    @JsonIgnoreProperties("room")
    private List<Reservation> reservations;
    public Room(){}
    public Room(Integer numberRoom, String roomType, Double price) {
        this.numberRoom = numberRoom;
        this.roomType = roomType;
        this.price = price;
    }

    public Integer getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(Integer numberRoom) {
        this.numberRoom = numberRoom;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
