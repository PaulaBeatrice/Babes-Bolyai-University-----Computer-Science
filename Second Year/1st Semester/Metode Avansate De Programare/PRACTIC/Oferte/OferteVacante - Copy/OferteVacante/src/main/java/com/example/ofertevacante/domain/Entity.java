package com.example.ofertevacante.domain;

import java.io.Serializable;
import java.util.Objects;

public class Entity<ID> implements Serializable {
    private ID id;

    public Entity(ID id) {
        this.id = id;
    }
    public ID getId() {
        return id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof Entity<?>)) {
            return false;
        }

        Entity entity = (Entity) obj;
        return Objects.equals(this.id, entity.id);
    }
}