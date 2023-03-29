package com.makaia.Hotel.controllers;

import com.makaia.Hotel.modules.Customer;
import com.makaia.Hotel.modules.Reservation;
import com.makaia.Hotel.modules.Room;

import com.makaia.Hotel.services.ReservationService;
import io.swagger.annotations.*;

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
@ApiOperation(value="reservation", notes= "this create a reservation", response = Reservation.class)
    @PostMapping("/reservation/customer/{idCustomer}/room/{idRoom}")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation register(@ApiParam(value = "reservation object", required = true) @RequestBody Reservation reservation,@ApiParam(value = "customer id", required = true) @PathVariable("idCustomer") int idCustomer,@ApiParam(value = "room id", required = true) @PathVariable("idRoom") int idRoom){
        return reservationService.create(reservation, idCustomer, idRoom);
    }

    @ApiResponses(value={
            @ApiResponse( code = 200, message = "type research success")
    })
    @ApiOperation(value="List's room", notes= "this research by type and date", response = Room.class)
    @GetMapping("/byType/reservationDate/{date}/reservationType/{type}")
    public List<Room> researchByType(@ApiParam(value = "reservation date", required = true) @PathVariable("date") String date,@ApiParam(value = "room type", required = true) @PathVariable("type") String type){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = LocalDate.parse(date, formatter);
        return reservationService.roomsByType(parsedDate, type);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message ="Everything is Ok"),
            @ApiResponse(code = 404, message ="That's an error in the client service"),
            @ApiResponse(code = 500, message ="That's an internal error"),
    })
    @ApiOperation(value="List's room", notes= "this research by date", response = Room.class)
    @GetMapping("/byType/reservationDate/{date}")
    public List<Room> researchByDate(@ApiParam(value = "reservation date", required = true) @PathVariable("date") String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = LocalDate.parse(date, formatter);
        return reservationService.roomsByDate(parsedDate);
    }
}
