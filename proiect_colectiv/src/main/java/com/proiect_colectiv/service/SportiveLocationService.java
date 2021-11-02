package com.proiect_colectiv.service;

import com.proiect_colectiv.model.SportiveLocation;
import com.proiect_colectiv.repository.ISportiveLocationRepo;

public class SportiveLocationService implements ISportiveLocationService{
    public ISportiveLocationRepo sportiveLocationRepo;

    public SportiveLocationService(ISportiveLocationRepo sportiveLocationRepo) {
        this.sportiveLocationRepo = sportiveLocationRepo;
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
