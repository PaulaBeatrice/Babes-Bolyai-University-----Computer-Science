package com.example.zboruri.domain;

public class Client extends Entity<Integer>{
    private String nume;
    private String username;

    public Client(Integer integer, String nume, String username) {
        super(integer);
        this.nume = nume;
        this.username = username;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
