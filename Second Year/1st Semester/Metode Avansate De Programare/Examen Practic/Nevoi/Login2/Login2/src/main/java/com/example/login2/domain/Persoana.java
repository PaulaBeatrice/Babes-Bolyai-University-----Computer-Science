package com.example.login2.domain;

public class Persoana extends Entity<Integer>{
    private String nume;
    private String prenume;
    private String username;
    private String parola;
    TipuriOrase oras;
    private String strada;
    private String nr_strada;
    private String telefon;

    public Persoana(Integer integer, String nume, String prenume, String username, String parola, TipuriOrase oras, String strada, String nr_strada, String telefon) {
        super(integer);
        this.nume = nume;
        this.prenume = prenume;
        this.username = username;
        this.parola = parola;
        this.oras = oras;
        this.strada = strada;
        this.nr_strada = nr_strada;
        this.telefon = telefon;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public TipuriOrase getOras() {
        return oras;
    }

    public void setOras(TipuriOrase oras) {
        this.oras = oras;
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public String getNr_strada() {
        return nr_strada;
    }

    public void setNr_strada(String nr_strada) {
        this.nr_strada = nr_strada;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return "Persoana{" +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", username='" + username + '\'' +
                ", parola='" + parola + '\'' +
                ", oras=" + oras +
                ", strada='" + strada + '\'' +
                ", nr_strada='" + nr_strada + '\'' +
                ", telefon='" + telefon + '\'' +
                '}';
    }
}
