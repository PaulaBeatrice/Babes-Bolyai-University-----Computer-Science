package com.example.model1.repos;

import com.example.model1.domains.Entity;

import java.util.Date;
import java.util.List;

public interface Repository<ID, E extends Entity<ID>> {
    E findEntity(ID id);

    List<E> findOne(ID id, Date startDate, Date endDate);

    Double findOneByName(String name);

    Iterable<E> findAllWithName(String name);

    E add(E entity);

    List<String> getAll();

    List<E> getAllEntitys();

    List<E> offers(Date date, Integer fidelityGrade);
}
