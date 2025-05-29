package com.example.spital.domain;

public class Pacient extends Entity<String>{
    private int varsta;
    private YesNo prematur;
    private String diagnostic_pr;
    private int gravitate;

    public Pacient(String s, int varsta, YesNo prematur, String diagnostic_pr, int gravitate) {
        super(s);
        this.varsta = varsta;
        this.prematur = prematur;
        this.diagnostic_pr = diagnostic_pr;
        this.gravitate = gravitate;
    }

    public int getVarsta() {
        return varsta;
    }

    public YesNo getPrematur() {
        return prematur;
    }

    public String getDiagnostic_pr() {
        return diagnostic_pr;
    }

    public int getGravitate() {
        return gravitate;
    }
}
