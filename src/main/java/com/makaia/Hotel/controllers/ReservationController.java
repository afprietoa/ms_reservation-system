package com.makaia.Hotel.controllers;

import com.makaia.Hotel.modules.Customer;
import com.makaia.Hotel.modules.Reservation;
import com.makaia.Hotel.modules.Room;
import com.makaia.Hotel.services.CustomerService;
import com.makaia.Hotel.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @PostMapping("/reservation/{id}")
    public ResponseEntity<Reservation> register(@RequestBody Reservation reservation, @PathVariable("id") int id){
        return reservationService.create(reservation, id);
    }
    @GetMapping("/byType/reservationDate/{date}/reservationType/{type}")
    public List<Room> researchByType(@PathVariable("date") String date, @PathVariable("type") String type){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = LocalDate.parse(date, formatter);
        return reservationService.roomsByType(parsedDate, type);
    }

    @GetMapping("/byType/reservationDate/{date}")
    public List<Room> researchByDate(@PathVariable("date") String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = LocalDate.parse(date, formatter);
        return reservationService.roomsByDate(parsedDate);
    }
}
