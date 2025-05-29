package com.example.ofertevacante.domain;

import java.time.LocalDateTime;

public class Reservation extends Entity<Double>{
    private Long id_client;
    private Double id_hotel;
    private LocalDateTime start_date;
    private int nr_nights;

    public Reservation(Double aDouble, Long id_client, Double id_hotel, LocalDateTime start_date, int nr_nights) {
        super(aDouble);
        this.id_client = id_client;
        this.id_hotel = id_hotel;
        this.start_date = start_date;
        this.nr_nights = nr_nights;
    }

    public Long getId_client() {
        return id_client;
    }

    public Double getId_hotel() {
        return id_hotel;
    }

    public LocalDateTime getStart_date() {
        return start_date;
    }

    public int getNr_nights() {
        return nr_nights;
    }
}
