package repository.memory;

import domain.Entity;
import domain.validator.ValidationException;
import domain.validator.Validator;
import jdk.jshell.spi.ExecutionControl;
import repository.Repository;

import java.util.HashMap;
import java.util.Map;

public abstract class InMemoryRepository<ID, E extends Entity<ID>> implements Repository<E,ID> {
    private Validator<E> validator;
    private Map<ID,E> entities;

    public InMemoryRepository(Validator<E> validator) {
        this.validator = validator;
        entities=new HashMap<ID,E>();
    }

    @Override
    public E findOne(ID e) {
        if(e == null)
            throw new IllegalArgumentException("The id can not be null");
        else
        {
            E entity = null;
            for(E ent : entities.values())
            {
                if(ent.getId() == e)
                    entity = ent;
            }
            return entity;
        }
    }

    @Override
    public Iterable<E> findAll() {
        return entities.values();
    }

    @Override
    public E save(E entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        if (entities.containsKey(entity.getId())) {
            return entity;
        }
        validator.validate(entity);
        entities.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public E delete(ID e) {
        if (e == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        return entities.remove(e);
    }

    @Override
    public E update(E entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        try{
            validator.validate(entity);
        }
        catch (ValidationException E)
        {
            System.out.println("The entity is not valid!");
        }
        return entities.put(entity.getId(), entity);
    }
}
