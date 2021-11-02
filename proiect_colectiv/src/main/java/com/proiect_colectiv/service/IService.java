package com.proiect_colectiv.service;

import com.proiect_colectiv.model.Entity;

public interface IService<E extends Entity> {
    E findOne(int id);

    Iterable<E> findAll();

    void save(E entity);

    void delete(int id);

    void update(E entity);
}
