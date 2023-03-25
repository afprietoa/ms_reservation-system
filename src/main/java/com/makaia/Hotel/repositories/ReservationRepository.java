package com.makaia.Hotel.repositories;

import com.makaia.Hotel.modules.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
}