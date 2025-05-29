package com.example.clinica.domain;

public class Medic extends Entity<Integer>{
    private int idSectie;
    private String nume;
    private int vechime;
    private Rezident rezident;

    public Medic(Integer integer, String nume, int idSectie, int vechime, Rezident rezident) {
        super(integer);
        this.idSectie = idSectie;
        this.nume = nume;
        this.vechime = vechime;
        this.rezident = rezident;
    }

    public int getIdSectie() {
        return idSectie;
    }

    public String getNume() {
        return nume;
    }

    public int getVechime() {
        return vechime;
    }

    public Rezident getRezident() {
        return rezident;
    }

    @Override
    public String toString() {
        return "Medic{" +
                "idSectie=" + idSectie +
                ", nume='" + nume + '\'' +
                ", vechime=" + vechime +
                ", rezident=" + rezident +
                '}';
    }
}
