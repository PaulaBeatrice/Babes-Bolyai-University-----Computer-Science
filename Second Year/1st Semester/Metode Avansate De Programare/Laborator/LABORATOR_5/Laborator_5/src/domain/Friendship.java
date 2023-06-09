package domain;

import java.time.LocalDateTime;

import static java.lang.Math.max;
import static java.lang.Math.min;


/**
 * Friendship class extends Entity<ID> with ID- Long
 */
public class Friendship extends Entity<Long> {
    private Long idUser1;
    private Long idUser2;

    private LocalDateTime friendsFrom;

    /**
     * Constructor for Frienship Object
     *
     * @param idUser1     - Long
     * @param idUser2     - Long
     * @param friendsFrom - LocalDateTime
     */
    public Friendship(Long idUser1, Long idUser2, LocalDateTime friendsFrom) {
        this.idUser1 = min(idUser1, idUser2);
        this.idUser2 = max(idUser1, idUser2);
        this.friendsFrom = friendsFrom;
    }

    /**
     * Getter for FriendsFrom - LocalDateTime
     *
     * @return friendsFrom - LocalDateTime
     */
    public LocalDateTime getFriendsFrom() {
        return friendsFrom;
    }

    /**
     * Setter friendsFrom
     *
     * @param friendsFrom - Local Date Time
     */
    public void setFriendsFrom(LocalDateTime friendsFrom) {
        this.friendsFrom = friendsFrom;
    }

    /**
     * Getter for IDUser1
     *
     * @return idUser1 - Long
     */
    public Long getIdUser1() {
        return idUser1;
    }

    /**
     * Setter for IDUser1
     *
     * @param idUser1 - Long
     */
    public void setIdUser1(Long idUser1) {
        this.idUser1 = idUser1;
    }

    /**
     * Getter for IDUser2
     *
     * @return idUser2 - Long
     */
    public Long getIdUser2() {
        return idUser2;
    }

    /**
     * Setter for IDUser2
     *
     * @param idUser2 - Long
     */
    public void setIdUser2(Long idUser2) {
        this.idUser2 = idUser2;
    }
}
