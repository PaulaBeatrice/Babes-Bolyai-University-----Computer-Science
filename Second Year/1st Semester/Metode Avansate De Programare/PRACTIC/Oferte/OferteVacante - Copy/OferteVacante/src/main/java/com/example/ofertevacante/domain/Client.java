package com.example.ofertevacante.domain;

public class Client extends Entity<Long>{
    private String name;
    private int fidelity_grade;
    private int varsta;
    private Hobbies hobby;

    public Client(Long aLong, String name, int fidelity_grade, int varsta, Hobbies hobby) {
        super(aLong);
        this.name = name;
        this.fidelity_grade = fidelity_grade;
        this.varsta = varsta;
        this.hobby = hobby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFidelity_grade() {
        return fidelity_grade;
    }

    public void setFidelity_grade(int fidelity_grade) {
        this.fidelity_grade = fidelity_grade;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public Hobbies getHobby() {
        return hobby;
    }

    public void setHobby(Hobbies hobby) {
        this.hobby = hobby;
    }
}
