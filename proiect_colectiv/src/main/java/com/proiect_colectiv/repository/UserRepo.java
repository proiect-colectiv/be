package com.proiect_colectiv.repository;

import com.proiect_colectiv.model.User;
import org.hibernate.SessionFactory;

public class UserRepo implements IUserRepo{
    static SessionFactory sessionFactory;

    public UserRepo(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
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
    public User findOneByUsername(String username) {
        return null;
    }
}
