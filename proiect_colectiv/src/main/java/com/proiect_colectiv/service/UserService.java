package com.proiect_colectiv.service;

import com.proiect_colectiv.model.User;
import com.proiect_colectiv.repository.IUserRepo;

public class UserService implements IUserService{
    public IUserRepo userRepo;
    @Override
    public User findOne(int id) {
        return null;
    }

    @Override
    public Iterable<User> findAll() {
        return null;
    }

    @Override
    public void save(User entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public User findUserByUsername(String username) {
        return null;
    }
}
