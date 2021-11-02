package com.proiect_colectiv.service;

import com.proiect_colectiv.model.Reservation;
import com.proiect_colectiv.repository.IRepo;
import com.proiect_colectiv.repository.IReservationRepo;

public class ReservationService implements IReservationService{
    public IReservationRepo reservationRepo;

    public ReservationService(IReservationRepo reservationRepo) {
        this.reservationRepo = reservationRepo;
    }

    @Override
    public Reservation findOne(int id) {
        return null;
    }

    @Override
    public Iterable<Reservation> findAll() {
        return null;
    }

    @Override
    public void save(Reservation entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Reservation entity) {

    }
}
