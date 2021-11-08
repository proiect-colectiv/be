package com.proiect_colectiv.repository.RepositoryInterfaces;

import com.proiect_colectiv.model.Entity;

public interface IRepo<E extends Entity> {
    E findOne(Long id);

    Iterable<E> findAll();

    void save(E entity);

    void delete(Long id);

    void update(E entity);

}
