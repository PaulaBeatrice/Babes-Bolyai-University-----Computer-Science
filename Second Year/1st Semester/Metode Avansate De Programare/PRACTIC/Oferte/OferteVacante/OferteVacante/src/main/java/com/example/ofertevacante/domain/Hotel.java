package com.example.ofertevacante.domain;

public class Hotel extends Entity<Double>{
    private Double locationId;
    private String name;
    private Integer nrCamere;
    private Double pret;
    private typeHotel tipHotel;

    public Hotel(Double aDouble, Double locationId, String name, Integer nrCamere, Double pret, typeHotel tipHotel) {
        super(aDouble);
        this.locationId = locationId;
        this.name = name;
        this.nrCamere = nrCamere;
        this.pret = pret;
        this.tipHotel = tipHotel;
    }

    public Double getLocationId() {
        return locationId;
    }

    public void setLocationId(Double locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNrCamere() {
        return nrCamere;
    }

    public void setNrCamere(Integer nrCamere) {
        this.nrCamere = nrCamere;
    }

    public Double getPret() {
        return pret;
    }

    public void setPret(Double pret) {
        this.pret = pret;
    }

    public typeHotel getTipHotel() {
        return tipHotel;
    }

    public void setTipHotel(typeHotel tipHotel) {
        this.tipHotel = tipHotel;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "locationId=" + locationId +
                ", name='" + name + '\'' +
                ", nrCamere=" + nrCamere +
                ", pret=" + pret +
                ", tipHotel=" + tipHotel +
                '}';
    }
}
