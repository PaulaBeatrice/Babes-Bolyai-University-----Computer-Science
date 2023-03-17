package com.example.login2.domain;

import java.time.LocalDateTime;

public class Nevoie extends Entity<Integer>{
    private String titlu;
    private String descriere;
    private LocalDateTime deadline;
    private Integer ominNevoie;
    private  Integer onSalvator;
    private String status;

    public Nevoie(Integer integer, String titlu, String descriere, LocalDateTime deadline, Integer ominNevoie, Integer onSalvator, String status) {
        super(integer);
        this.titlu = titlu;
        this.descriere = descriere;
        this.deadline = deadline;
        this.ominNevoie = ominNevoie;
        this.onSalvator = onSalvator;
        this.status = status;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public Integer getOminNevoie() {
        return ominNevoie;
    }

    public void setOminNevoie(Integer ominNevoie) {
        this.ominNevoie = ominNevoie;
    }

    public Integer getOnSalvator() {
        return onSalvator;
    }

    public void setOnSalvator(Integer onSalvator) {
        this.onSalvator = onSalvator;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Nevoie{" +
                "titlu='" + titlu + '\'' +
                ", descriere='" + descriere + '\'' +
                ", deadline=" + deadline +
                ", ominNevoie=" + ominNevoie +
                ", onSalvator=" + onSalvator +
                ", status='" + status + '\'' +
                '}';
    }
}
