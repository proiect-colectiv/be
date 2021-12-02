package com.proiect_colectiv.service;

import com.proiect_colectiv.model.SportiveLocation;
import com.proiect_colectiv.repository.RepositoryInterfaces.ISportiveLocationRepo;

public class SportiveLocationService implements ISportiveLocationService{
    public ISportiveLocationRepo sportiveLocationRepo;

    public SportiveLocationService(ISportiveLocationRepo sportiveLocationRepo) {
        this.sportiveLocationRepo = sportiveLocationRepo;
    }

    @Override
    public SportiveLocation findOne(Long id) {
        return sportiveLocationRepo.findOne(id);
    }

    @Override
    public Iterable<SportiveLocation> findAll() {
        return sportiveLocationRepo.findAll();
    }

    @Override
    public void save(SportiveLocation entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(SportiveLocation entity) {

    }
}
