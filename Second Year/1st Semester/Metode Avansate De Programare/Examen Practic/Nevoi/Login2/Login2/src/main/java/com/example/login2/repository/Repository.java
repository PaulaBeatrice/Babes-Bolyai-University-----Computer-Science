package com.example.login2.repository;

import com.example.login2.domain.Entity;

import java.util.Map;

public interface Repository <ID, E extends Entity<ID>> {
    E find(ID id) throws IllegalArgumentException;

    E save(E entity);

    Iterable <E> getAll();
    public Map<ID,E> getMap();
    void clear();

    E update(E entity);
}
