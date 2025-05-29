package com.example.examen.domain;

public class City extends Entity<String>{
    private String name;

    public City(String s, String name) {
        super(s);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
