package domain;

public class Participant extends Entity<Integer>{
    private String first_name;
    private String last_name;
    private int points;

    public Participant(Integer integer, String first_name, String last_name, int points) {
        super(integer);
        this.first_name = first_name;
        this.last_name = last_name;
        this.points = points;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", points=" + points +
                '}';
    }
}
