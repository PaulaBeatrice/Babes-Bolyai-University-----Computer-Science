package com.example.restaurant.domain;

import java.io.Serializable;
import java.util.Objects;

/*
* Class that models an entity having an id
* @param <ID> any type
 */
public class Entity<ID> implements Serializable {
    private static final long serialVersionUID = 7331115341259248461L;
    private ID id;

    public Entity(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public int hashCode(){return Objects.hash(this.id);}

    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }
        if(!(obj instanceof Entity<?>)){
            return false;
        }
        Entity entity = (Entity) obj;
        return Objects.equals(this.id,entity.id);
    }
}
