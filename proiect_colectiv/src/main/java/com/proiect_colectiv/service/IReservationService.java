package com.proiect_colectiv.service;

import com.proiect_colectiv.model.FilterDTO;
import com.proiect_colectiv.model.Reservation;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IReservationService extends IService<Reservation> {
    Iterable<Reservation> filterReservationByLocation(Long locationID);
    Iterable<Reservation> filterReservationByDay(LocalDate time);
    Iterable<Reservation> getAllReservationsAfterDate(LocalDateTime time);
    Iterable<Reservation> getFutureReservations();
    Iterable<Reservation> getReservationsFiltered(FilterDTO filter);
}
