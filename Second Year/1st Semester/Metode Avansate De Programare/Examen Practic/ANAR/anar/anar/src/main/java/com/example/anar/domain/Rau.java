package com.example.anar.domain;

public class Rau extends Entity<Integer>{
    private String nume;
    private int cota_medie;

    public Rau(Integer integer, String nume, int cota_medie) {
        super(integer);
        this.nume = nume;
        this.cota_medie = cota_medie;
    }

    public String getNume() {
        return nume;
    }

    public int getCota_medie() {
        return cota_medie;
    }

    public void setCota_medie(int cota_medie) {
        this.cota_medie = cota_medie;
    }
}
