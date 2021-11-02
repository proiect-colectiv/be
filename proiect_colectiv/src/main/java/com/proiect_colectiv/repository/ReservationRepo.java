package com.proiect_colectiv.repository;

import com.proiect_colectiv.model.Reservation;
import org.hibernate.SessionFactory;

public class ReservationRepo implements IReservationRepo{
    static SessionFactory sessionFactory;

    public ReservationRepo(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
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
