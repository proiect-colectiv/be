package com.proiect_colectiv.repository.RepositoryInterfaces;

import com.proiect_colectiv.model.ReservationUser;

public interface IReservationUserRepo extends IRepo<ReservationUser> {
    Iterable<ReservationUser> filterByUserID(Long userID);
    Iterable<ReservationUser> filterByReservationID(Long reservationID);
}
