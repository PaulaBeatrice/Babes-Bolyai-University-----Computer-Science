package repository;

import domain.Entity;

import java.util.List;

/**
 * CRUD operations repository interface
 * @param <ID> - type E must have an attribute of type ID
 * @param <E> -  type of entities saved in repository
 */
public interface IRepository<ID, E extends Entity<ID>> {

    /**
     * Returns the entity that has the given id
     * @param id ID - the id of the entity to be returned
     *           id must not be null
     * @return the entity with the specified id
     * @throws IllegalArgumentException if the id is null.
     */
    E findOne(ID id) throws IllegalArgumentException;

    /**
     * Returns all entities in the repository
     * @return - an iterable object that contains all entities
     */
    List<E> getAll();

    /**
     * Clears the repository's data
     */
    void clear();

    /**
     * Add an entity
     */
    void save(E entity);

    /**
     * Update an entity
     */
    void update(E entity);

    /**
     * Delete an entity
     */
    void delete(ID id);
}


