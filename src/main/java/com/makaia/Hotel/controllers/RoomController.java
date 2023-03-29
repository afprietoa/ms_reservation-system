package com.makaia.Hotel.controllers;

import com.makaia.Hotel.modules.Customer;
import com.makaia.Hotel.modules.Room;
import com.makaia.Hotel.services.RoomService;
import io.swagger.annotations.*;
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
    @ApiOperation(value="room", notes= "this create a room", response = Room.class)
    @PostMapping("/room")
    @ResponseStatus(HttpStatus.CREATED)
    public Room register(@ApiParam(value = "room object", required = true) @RequestBody Room room){
        return roomService.create(room);
    }
}