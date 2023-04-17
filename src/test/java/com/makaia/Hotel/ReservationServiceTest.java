package com.makaia.Hotel;

import com.makaia.Hotel.exceptions.HandlerResponseException;
import com.makaia.Hotel.modules.Customer;
import com.makaia.Hotel.modules.Reservation;
import com.makaia.Hotel.modules.Room;
import com.makaia.Hotel.repositories.CustomerRepository;
import com.makaia.Hotel.repositories.ReservationRepository;
import com.makaia.Hotel.repositories.RoomRepository;
import com.makaia.Hotel.services.ReservationService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReservationServiceTest {

    ReservationRepository reservationRepository;
    CustomerRepository customerRepository;
    RoomRepository roomRepository;
    ReservationService reservationService;


    @Before
    public void setUp() {
        this.reservationRepository = mock(ReservationRepository.class);
        this.customerRepository = mock(CustomerRepository.class);
        this.roomRepository = mock(RoomRepository.class);
        this.reservationService = new ReservationService(reservationRepository, roomRepository, customerRepository);
    }

    @Test
    public void validateReservation() {
        Customer customer = new Customer(1, "Pepito", "Peréz", "Calle falsa 123", 18, "peto@example.com");
        Room room = new Room(34, "basic", 100.00);

        Reservation reservation = new Reservation(1, LocalDate.of(2024, 3, 31), 100.00, customer, room);
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);

        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        when(customerRepository.findAll()).thenReturn(customerList);

        when(roomRepository.findById(34)).thenReturn(Optional.of(room));



        Reservation reserv = reservationService.create(reservation, 1,34);

        LocalDate nowDate = LocalDate.now();


        assertTrue((reserv.getReserveDate().isAfter(nowDate) &&
                customerList.contains(customer) &&
                reserv.getRoom() != null));
    }

    @Test
    public void validateRoomsByType() {
        List<Reservation> reservationList = new ArrayList<>();

        Customer customer1 = new Customer(1, "Pepito", "Peréz", "Calle falsa 123", 18, "peto@example.com");
        Room room1 = new Room(1, "basic", 100.00);

        Reservation reservation1 = new Reservation(1, LocalDate.of(2024, 3, 30), 100.00, customer1, room1);

        reservationList.add(reservation1);

        Customer customer2 = new Customer(2, "Mariana", "Rodrigue", "Calle falsa 123", 18, "peto@example.com");
        Room room2 = new Room(2, "premium", 100.00);

        Reservation reservation2 = new Reservation(1, LocalDate.of(2024, 3, 30), 100.00, customer2, room2);

        reservationList.add(reservation2);

        Customer customer3 = new Customer(3, "Luis", "Doe", "Calle falsa 123", 18, "peto@example.com");
        Room room3 = new Room(3, "basic", 100.00);

        Reservation reservation3 = new Reservation(1, LocalDate.of(2024, 3, 29), 100.00, customer3, room3);

        reservationList.add(reservation3);

        Customer customer4 = new Customer(4, "Sofi", "Poe", "Calle falsa 123", 18, "peto@example.com");
        Room room4 = new Room(4, "basic", 100.00);

        Reservation reservation4 = new Reservation(1, LocalDate.of(2024, 3, 28), 100.00, customer3, room3);

        reservationList.add(reservation4);

        Customer customer5 = new Customer(5, "Rita", "Rub", "Calle falsa 123", 18, "peto@example.com");
        Room room5 = new Room(5, "premium", 100.00);

        Reservation reservation5 = new Reservation(5, LocalDate.of(2024, 3, 30), 100.00, customer3, room3);

        reservationList.add(reservation5);

        when(reservationRepository.findAll()).thenReturn(reservationList);
        for (int i = 0; i < reservationList.size(); i++) {
            when(roomRepository.findById(i)).thenReturn(Optional.of(reservationList.get(i).getRoom()));
        }


        List<Room> roomList = reservationService.roomsByType("2024-03-30", "basic");

        assertNotNull(roomList);
        assertTrue(roomList.get(0).equals(room1));
    }

    @Test
    public void validateRoomsByDate() {
        List<Reservation> reservationList = new ArrayList<>();

        Customer customer1 = new Customer(1, "Pepito", "Peréz", "Calle falsa 123", 18, "peto@example.com");
        Room room1 = new Room(1, "basic", 100.00);

        Reservation reservation1 = new Reservation(1, LocalDate.of(2027, 3, 30), 100.00, customer1, room1);

        reservationList.add(reservation1);

        Customer customer2 = new Customer(2, "Mariana", "Rodrigue", "Calle falsa 123", 18, "peto@example.com");
        Room room2 = new Room(2, "basic", 100.00);

        Reservation reservation2 = new Reservation(1, LocalDate.of(2027, 3, 30), 100.00, customer2, room2);

        reservationList.add(reservation2);

        Customer customer3 = new Customer(3, "Luis", "Doe", "Calle falsa 123", 18, "peto@example.com");
        Room room3 = new Room(3, "basic", 100.00);

        Reservation reservation3 = new Reservation(1, LocalDate.of(2027, 3, 29), 100.00, customer3, room3);

        reservationList.add(reservation3);

        Customer customer4 = new Customer(4, "Sofi", "Poe", "Calle falsa 123", 18, "peto@example.com");
        Room room4 = new Room(4, "basic", 100.00);

        Reservation reservation4 = new Reservation(1, LocalDate.of(2027, 3, 28), 100.00, customer3, room3);

        reservationList.add(reservation4);

        Customer customer5 = new Customer(5, "Rita", "Rub", "Calle falsa 123", 18, "peto@example.com");
        Room room5 = new Room(5, "basic", 100.00);

        Reservation reservation5 = new Reservation(5, LocalDate.of(2027, 3, 30), 100.00, customer3, room3);

        reservationList.add(reservation5);

        when(reservationRepository.findAll()).thenReturn(reservationList);
        for (int i = 0; i < reservationList.size(); i++) {
            when(roomRepository.findById(i)).thenReturn(Optional.of(reservationList.get(i).getRoom()));
        }

//        when("2027-03-30".matches(any())).thenReturn(true);
        List<Room> roomList = reservationService.roomsByDate("2027-03-30");


        assertNotNull(roomList);
        assertTrue(roomList.get(0).equals(room1));
    }

    @Test
    public void testRoomsByTypeBasic() {
        // Mock data
        Customer customer1 = new Customer(1, "John", "Doe", "12345678",43,"Cr java");
        Room room1 = new Room(1, "basic", 50.0);
        Room room2 = new Room(2, "basic", 60.0);
        String date = "2027-04-30";

        Reservation reservation1 = new Reservation(1, LocalDate.of(2027, 4, 30), 50.0, customer1, room1);
        Reservation reservation2 = new Reservation(2, LocalDate.of(2027, 4, 30), 60.0, customer1, room2);

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation1);
        reservations.add(reservation2);

        // Mock repository behavior
        when(reservationRepository.findAll()).thenReturn(reservations);

        // Call the service method
        List<Room> roomList = reservationService.roomsByType(date, "basic");

        // Assertions
        assertEquals(2, roomList.size());
        assertEquals(room1, roomList.get(0));
        assertEquals(room2, roomList.get(1));
    }

    @Test
    public void testRoomsByTypePremium() {
        // Mock data
        Customer customer1 = new Customer(1, "John", "Doe", "12345678",43,"Cr java");
        Room room1 = new Room(1, "premium", 50.0);
        Room room2 = new Room(2, "premium", 60.0);
        String date = "2027-04-30";

        Reservation reservation1 = new Reservation(1, LocalDate.of(2027, 4, 30), 50.0, customer1, room1);
        Reservation reservation2 = new Reservation(2, LocalDate.of(2027, 4, 30), 60.0, customer1, room2);

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation1);
        reservations.add(reservation2);

        // Mock repository behavior
        when(reservationRepository.findAll()).thenReturn(reservations);

        // Call the service method
        List<Room> roomList = reservationService.roomsByType(date, "premium");

        // Assertions
        assertEquals(2, roomList.size());
        assertEquals(room1, roomList.get(0));
        assertEquals(room2, roomList.get(1));
    }

    @Test
    public void testResearchWithEmptyRepository() {
        when(reservationRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(HandlerResponseException.class, () -> {
            reservationService.research();
        });
    }

    @Test
    public void testCreateWithExistingReservation() {
        Customer customer = new Customer(1, "Pepito", "Peréz", "Calle falsa 123", 18, "peto@example.com");
        Room room = new Room(34, "basic", 100.00);

        Reservation reservation = new Reservation(1, LocalDate.of(2024, 3, 31), 100.00, customer, room);
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);

        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        when(customerRepository.findAll()).thenReturn(customerList);

        when(roomRepository.findById(34)).thenReturn(Optional.of(room));

        when(reservationRepository.findById(reservation.getReserveCode()))
                .thenReturn(Optional.of(reservation));

        HandlerResponseException exception = assertThrows(
                HandlerResponseException.class,
                () -> reservationService.create(reservation, 1,34)
        );

        assertAll(
                () -> assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus()),
                () -> assertEquals("Reservation isn't available.", exception.getReason())
        );


    }

    @Test
    public void testCreateWithInvalidDate() {
        Customer customer = new Customer(1, "Pepito", "Peréz", "Calle falsa 123", 18, "peto@example.com");
        Room room = new Room(34, "basic", 100.00);

        Reservation reservation = new Reservation(1, LocalDate.of(2022, 3, 31), 100.00, customer, room);
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);

        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        when(customerRepository.findAll()).thenReturn(customerList);

        when(roomRepository.findById(34)).thenReturn(Optional.of(room));

        HandlerResponseException exception = assertThrows(
                HandlerResponseException.class,
                () -> reservationService.create(reservation, 1,34)
        );

        assertAll(
                () -> assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus()),
                () -> assertEquals("Reservation isn't available for " + LocalDate.now(), exception.getReason())
        );

    }

}

