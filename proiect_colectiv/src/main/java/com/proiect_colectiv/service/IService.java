package com.proiect_colectiv.service;

import com.proiect_colectiv.model.Entity;
import org.springframework.stereotype.Service;

public interface IService<E extends Entity> {
    E findOne(Long id);

    Iterable<E> findAll();

    void save(E entity);

    void delete(Long id);

    void update(E entity);
}
