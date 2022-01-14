package com.proiect_colectiv.model;

public class ReservationUser extends Entity {
    private User user;
    private Reservation reservation;

    public ReservationUser(Long id, User user, Reservation reservation) {
        super(id);
        this.user = user;
        this.reservation = reservation;
    }

    public ReservationUser(User user, Reservation reservation) {
        this.user = user;
        this.reservation = reservation;
    }

    public ReservationUser() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public String toString() {
        return "ReservationUser{" +
                "user=" + user +
                ", reservation=" + reservation +
                '}';
    }
}
