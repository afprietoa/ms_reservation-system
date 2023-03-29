package com.makaia.Hotel.controllers;

import com.makaia.Hotel.modules.Reservation;
import com.makaia.Hotel.modules.Room;

import com.makaia.Hotel.services.ReservationService;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api(value="---", description = "This is communication between reservation controller and all associative reservation services")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
@ApiResponses(value={
        @ApiResponse( code = 201, message = "created reservation success")
})
    @PostMapping("/reservation/customer/{idCustomer}/room/{idRoom}")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation register(@RequestBody Reservation reservation, @PathVariable("idCustomer") int idCustomer, @PathVariable("idRoom") int idRoom){
        return reservationService.create(reservation, idCustomer, idRoom);
    }

    @ApiResponses(value={
            @ApiResponse( code = 200, message = "type research success")
    })
    @GetMapping("/byType/reservationDate/{date}/reservationType/{type}")
    public List<Room> researchByType(@PathVariable("date") String date, @PathVariable("type") String type){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = LocalDate.parse(date, formatter);
        return reservationService.roomsByType(parsedDate, type);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message ="Everything is Ok"),
            @ApiResponse(code = 404, message ="That's an error in the client service"),
            @ApiResponse(code = 500, message ="That's an internal error"),
    })
    @GetMapping("/byType/reservationDate/{date}")
    public List<Room> researchByDate(@PathVariable("date") String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = LocalDate.parse(date, formatter);
        return reservationService.roomsByDate(parsedDate);
    }
}
