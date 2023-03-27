package com.makaia.Hotel;

import com.makaia.Hotel.modules.Customer;
import com.makaia.Hotel.modules.Reservation;
import com.makaia.Hotel.modules.Room;
import com.makaia.Hotel.repositories.CustomerRepository;
import com.makaia.Hotel.repositories.ReservationRepository;
import com.makaia.Hotel.repositories.RoomRepository;
import com.makaia.Hotel.services.CustomerService;
import com.makaia.Hotel.services.ReservationService;
import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class ReservationServiceTest {

    ReservationRepository reservationRepository;
    CustomerRepository customerRepository;
    RoomRepository roomRepository;
    ReservationService reservationService;



    @Before
    public void setUp(){
        this.reservationRepository = mock(ReservationRepository.class);
        this.customerRepository = mock(CustomerRepository.class);
        this.roomRepository = mock(RoomRepository.class);
        this.reservationService = new ReservationService(reservationRepository, roomRepository,customerRepository);
    }

    @Test
    public void validateReservation() {
        Customer customer = new Customer(1, "Pepito", "Peréz", "Calle falsa 123", 18, "peto@example.com");
        Room room = new Room(1, "basic", 100.00);

        Reservation reservation = new Reservation(1, LocalDate.of(2023,3,30), 100.00, customer, room);


        LocalDate nowDate = LocalDate.now();
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);

        assertTrue((reservation.getReserveDate().isAfter(nowDate) &&
                customerList.contains(customer) &&
                reservation.getReserveDate() != null &&
                reservation.getRoom() != null));
    }
}
