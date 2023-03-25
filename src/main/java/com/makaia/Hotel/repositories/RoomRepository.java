package com.makaia.Hotel.repositories;

import com.makaia.Hotel.modules.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Integer> {
}