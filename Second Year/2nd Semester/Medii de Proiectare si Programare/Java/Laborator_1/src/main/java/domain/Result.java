package domain;

public class Result extends Entity<Integer>{
    private Participant participant;
    private Referee referee;
    private String activity;
    private int points;

    public Result(Integer integer, Participant participant, Referee referee, String activity, int points) {
        super(integer);
        this.participant = participant;
        this.referee = referee;
        this.activity = activity;
        this.points = points;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public Referee getReferee() {
        return referee;
    }

    public void setReferee(Referee referee) {
        this.referee = referee;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
