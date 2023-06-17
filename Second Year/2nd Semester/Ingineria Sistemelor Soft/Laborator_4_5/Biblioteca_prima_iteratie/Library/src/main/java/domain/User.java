package domain;

public interface User extends Entity<Integer>{
    public String getUsername();
    public String getPassword();
    public void setUsername(String username);
    public void setPassword(String password);
}
