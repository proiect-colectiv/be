package com.proiect_colectiv.repository;

import com.proiect_colectiv.model.SportiveLocation;
import org.hibernate.SessionFactory;

public class SportiveLocationRepo implements ISportiveLocationRepo{
    static SessionFactory sessionFactory;

    public SportiveLocationRepo(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public SportiveLocation findOne(int id) {
        return null;
    }

    @Override
    public Iterable<SportiveLocation> findAll() {
        return null;
    }

    @Override
    public void save(SportiveLocation entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(SportiveLocation entity) {

    }
}
