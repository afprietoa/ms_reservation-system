package com.makaia.Hotel.controllers;

import com.makaia.Hotel.modules.Room;
import com.makaia.Hotel.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RoomController {
    @Autowired
    private RoomService roomService;
    @PostMapping("/room")
    public ResponseEntity<Room> register(@RequestBody Room room){
        return roomService.create(room);
    }
}