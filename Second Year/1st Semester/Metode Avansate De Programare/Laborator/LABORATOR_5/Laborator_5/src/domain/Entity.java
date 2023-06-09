package domain;

import java.io.Serializable;

/**
 * Generic class Entity for business
 */
public class Entity<ID> implements Serializable {
    private static final long serialVersionUID = 7331115341259248461L;
    private ID id;

    /**
     * Getter for generic Id
     */
    public ID getId() {
        return id;
    }

    /**
     * Setter for generic id
     *
     * @param id - generic type
     */
    public void setId(ID id) {
        this.id = id;
    }
}
