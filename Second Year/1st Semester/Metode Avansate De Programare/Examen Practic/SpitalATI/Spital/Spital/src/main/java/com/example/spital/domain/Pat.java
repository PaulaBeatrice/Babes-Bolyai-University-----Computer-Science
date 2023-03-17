package com.example.spital.domain;

public class Pat extends Entity<Integer>{
    private TipPat tip_pat;
    private YesNo ventilatie;
    private String pacient_cnp;

    public Pat(Integer integer, TipPat tip_pat, YesNo ventilatie, String pacient_cnp) {
        super(integer);
        this.tip_pat = tip_pat;
        this.ventilatie = ventilatie;
        this.pacient_cnp = pacient_cnp;
    }

    public void setPacient_cnp(String pacient_cnp) {
        this.pacient_cnp = pacient_cnp;
    }

    public TipPat getTip_pat() {
        return tip_pat;
    }

    public YesNo getVentilatie() {
        return ventilatie;
    }

    public String getPacient_cnp() {
        return pacient_cnp;
    }
}
