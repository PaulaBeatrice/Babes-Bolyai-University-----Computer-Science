package domain;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Friendship extends Entity<Long>{
        Long idUser1;
        Long idUser2;

    public Friendship(Long idUser1, Long idUser2) {
        this.idUser1 = min(idUser1,idUser2);
        this.idUser2 = max(idUser1,idUser2);
    }

    public Long getIdUser1() {
        return idUser1;
    }

    public void setIdUser1(Long idUser1) {
        this.idUser1 = idUser1;
    }

    public Long getIdUser2() {
        return idUser2;
    }

    public void setIdUser2(Long idUser2) {
        this.idUser2 = idUser2;
    }
}
