package com.example.examen.repository;

import com.example.examen.domain.Entity;
import com.example.examen.exceptions.InexistentEntityException;

import java.util.Map;

/**
 * CRUD operations repository interface
 * @param <ID> - type E must have an attribute of type ID
 * @param <E> -  type of entities saved in repository
 */
public interface Repository<ID, E extends Entity<ID>> {



    /**
     * Returns all entities in the repository
     * @return - an iterable object that contains all entities
     */
    Iterable<E> getAll();


}

