package domain;

public class Referee extends Entity<Integer>{
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String activity;

    public Referee(Integer integer, String username, String password, String first_name, String last_name, String activity) {
        super(integer);
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.activity = activity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "Referee{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", activity=" + activity +
                '}';
    }
}
