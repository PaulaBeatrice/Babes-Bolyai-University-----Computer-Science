package grades;

import java.util.Objects;

public class Tema {
    private String desc;
    private String id;

    public Tema(String desc, String id) {
        this.desc = desc;
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Tema{" +
                "desc='" + desc + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tema tema = (Tema) o;
        return desc.equals(tema.desc) && id.equals(tema.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(desc, id);
    }
}
