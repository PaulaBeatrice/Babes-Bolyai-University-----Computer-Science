package com.example.clinica.domain;

public class Sectie extends Entity<Integer>{
    private String nume;
    private int idSefDeSectie;
    private int pretPerConsulattie;
    private int durataMaximaConsultatie;

    public Sectie(Integer integer, String nume, int idSefDeSectie, int pretPerConsulattie, int durataMaximaConsultatie) {
        super(integer);
        this.nume = nume;
        this.idSefDeSectie = idSefDeSectie;
        this.pretPerConsulattie = pretPerConsulattie;
        this.durataMaximaConsultatie = durataMaximaConsultatie;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getIdSefDeSectie() {
        return idSefDeSectie;
    }

    public void setIdSefDeSectie(int idSefDeSectie) {
        this.idSefDeSectie = idSefDeSectie;
    }

    public int getPretPerConsulattie() {
        return pretPerConsulattie;
    }

    public void setPretPerConsulattie(int pretPerConsulattie) {
        this.pretPerConsulattie = pretPerConsulattie;
    }

    public int getDurataMaximaConsultatie() {
        return durataMaximaConsultatie;
    }

    public void setDurataMaximaConsultatie(int durataMaximaConsultatie) {
        this.durataMaximaConsultatie = durataMaximaConsultatie;
    }

    @Override
    public String toString() {
        return "Sectie{" +
                "nume='" + nume + '\'' +
                ", idSefDeSectie=" + idSefDeSectie +
                ", pretPerConsulattie=" + pretPerConsulattie +
                ", durataMaximaConsultatie=" + durataMaximaConsultatie +
                '}';
    }
}
