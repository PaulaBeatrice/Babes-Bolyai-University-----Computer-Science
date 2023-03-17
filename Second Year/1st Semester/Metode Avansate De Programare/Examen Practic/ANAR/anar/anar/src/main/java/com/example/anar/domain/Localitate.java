package com.example.anar.domain;

public class Localitate extends Entity<Integer>{
    private String nume;
    private Integer rau;
    private Integer cota_minima;
    private Integer cota_maxima;

    public Localitate(Integer integer, String nume, Integer rau, Integer cota_minima, Integer cota_maxima) {
        super(integer);
        this.nume = nume;
        this.rau = rau;
        this.cota_minima = cota_minima;
        this.cota_maxima = cota_maxima;
    }

    public String getNume() {
        return nume;
    }

    public Integer getRau() {
        return rau;
    }

    public Integer getCota_minima() {
        return cota_minima;
    }

    public Integer getCota_maxima() {
        return cota_maxima;
    }
}
