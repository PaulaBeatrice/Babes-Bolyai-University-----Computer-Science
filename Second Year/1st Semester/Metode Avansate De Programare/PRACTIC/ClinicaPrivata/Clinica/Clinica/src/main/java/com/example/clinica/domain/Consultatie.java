package com.example.clinica.domain;

import java.time.LocalDateTime;

public class Consultatie extends Entity<Integer>{
    private int idMedic;
    private String CNPPacient;
    private String numePacient;
    private LocalDateTime data;
    private int ora;

    public Consultatie(Integer integer, int idMedic, String CNPPacient, String numePacient, LocalDateTime data, int ora) {
        super(integer);
        this.idMedic = idMedic;
        this.CNPPacient = CNPPacient;
        this.numePacient = numePacient;
        this.data = data;
        this.ora = ora;
    }

    public int getIdMedic() {
        return idMedic;
    }

    public String getCNPPacient() {
        return CNPPacient;
    }

    public String getNumePacient() {
        return numePacient;
    }

    public LocalDateTime getData() {
        return data;
    }

    public int getOra() {
        return ora;
    }

    @Override
    public String toString() {
        return "Consultatie{" +
                "idMedic=" + idMedic +
                ", CNPPacient='" + CNPPacient + '\'' +
                ", numePacient='" + numePacient + '\'' +
                ", data=" + data +
                ", ora=" + ora +
                '}';
    }
}
