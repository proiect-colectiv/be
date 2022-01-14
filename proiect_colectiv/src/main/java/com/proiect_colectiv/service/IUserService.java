package com.proiect_colectiv.service;

import com.proiect_colectiv.model.User;

public interface IUserService extends IService<User>{
    public User findUserByUsername(String username);
    public boolean validateUser(User user);
    Iterable<User> getUsersForReservationId(Long reservationId);
}
