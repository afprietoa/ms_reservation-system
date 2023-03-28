package com.makaia.Hotel;

import com.makaia.Hotel.modules.Customer;
import com.makaia.Hotel.modules.Room;
import com.makaia.Hotel.repositories.CustomerRepository;
import com.makaia.Hotel.repositories.RoomRepository;
import com.makaia.Hotel.services.CustomerService;
import com.makaia.Hotel.services.RoomService;
import org.junit.Test;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class RoomServiceTest {
    RoomRepository roomRepository;
    RoomService roomService;

    @Before
    public void setUp(){
        this.roomRepository = mock(RoomRepository.class);
        this.roomService = new RoomService(roomRepository);
    }

    @Test
    public void validateRoom() {
        Room room = new Room(1, "basic", 100.00);
        Room room2 = roomService.create(room);

        assertNotNull(room2);
    }
}
