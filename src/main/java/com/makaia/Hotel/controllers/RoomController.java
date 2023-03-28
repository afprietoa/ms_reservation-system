package com.makaia.Hotel.controllers;

import com.makaia.Hotel.modules.Customer;
import com.makaia.Hotel.modules.Room;
import com.makaia.Hotel.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class RoomController {
    @Autowired
    private RoomService roomService;
/*    @PostMapping("/room")
    public ResponseEntity<Room> register(@RequestBody Room room){
        return roomService.create(room);
    }*/
    @PostMapping("/room")
    @ResponseStatus(HttpStatus.CREATED)
    public Room register(@RequestBody Room room){
        return roomService.create(room);
    }
}