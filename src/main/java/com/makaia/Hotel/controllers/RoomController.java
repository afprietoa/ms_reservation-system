package com.makaia.Hotel.controllers;

import com.makaia.Hotel.modules.Customer;
import com.makaia.Hotel.modules.Room;
import com.makaia.Hotel.services.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Api(value="---", description = "This is communication between room controller and all associative room services")
public class RoomController {
    @Autowired
    private RoomService roomService;
    @ApiResponses(value={
            @ApiResponse( code = 201, message = "created room success")
    })
    @PostMapping("/room")
    @ResponseStatus(HttpStatus.CREATED)
    public Room register(@RequestBody Room room){
        return roomService.create(room);
    }
}