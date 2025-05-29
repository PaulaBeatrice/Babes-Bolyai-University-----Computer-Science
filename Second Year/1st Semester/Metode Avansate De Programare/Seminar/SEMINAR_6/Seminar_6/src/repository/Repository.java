package repository;

import domain.Entity;

public interface Repository <ID, E extends Entity<ID>> {
    E findOne(ID id);

    Iterable<E> findall();

    E save(E entity);

    E delete(ID id);

    E update(E entity);
}
