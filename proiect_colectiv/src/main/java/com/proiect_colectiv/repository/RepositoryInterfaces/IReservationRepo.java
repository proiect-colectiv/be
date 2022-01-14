package com.proiect_colectiv.repository.RepositoryInterfaces;

import com.proiect_colectiv.model.Reservation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface IReservationRepo extends IRepo<Reservation> {
    Iterable<Reservation> filterReservationByLocation(Long locationID);
    Iterable<Reservation> filterReservationByDay(LocalDate time);
    Iterable<Reservation> getAllReservationsAfterDate(LocalDateTime time);
    Iterable<Reservation> getFutureReservations();
}
