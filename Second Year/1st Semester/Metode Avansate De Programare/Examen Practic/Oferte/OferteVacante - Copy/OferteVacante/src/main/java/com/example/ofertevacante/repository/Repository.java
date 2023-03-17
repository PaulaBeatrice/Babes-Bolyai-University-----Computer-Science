package com.example.ofertevacante.repository;

import com.example.ofertevacante.domain.Entity;

import java.util.Map;

public interface Repository <ID, E extends Entity<ID>> {
    E find(ID id) throws IllegalArgumentException;

    E save(E entity);

    Iterable <E> getAll();
    public Map<ID,E> getMap();
    void clear();
}
