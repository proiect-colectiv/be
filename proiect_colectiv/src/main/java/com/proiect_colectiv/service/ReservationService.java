package com.proiect_colectiv.service;

import com.proiect_colectiv.model.Reservation;
import com.proiect_colectiv.repository.RepositoryInterfaces.IReservationRepo;

public class ReservationService implements IReservationService{
    public IReservationRepo reservationRepo;

    public ReservationService(IReservationRepo reservationRepo) {
        this.reservationRepo = reservationRepo;
    }

    @Override
    public Reservation findOne(Long id) {
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
    public void delete(Long id) {

    }

    @Override
    public void update(Reservation entity) {

    }
}
