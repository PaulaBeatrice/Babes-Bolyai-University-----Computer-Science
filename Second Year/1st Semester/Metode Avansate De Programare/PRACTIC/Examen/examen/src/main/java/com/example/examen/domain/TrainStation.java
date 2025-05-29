package com.example.examen.domain;

public class TrainStation extends Entity<String>{
    private String departure_city;
    private String destination_city;

    private float price_per_station;

    public TrainStation(String s, String departure_city, String destination_city, float price_per_station) {
        super(s);
        this.departure_city = departure_city;
        this.destination_city = destination_city;
        this.price_per_station = price_per_station;
    }

    public float getPrice_per_station() {
        return price_per_station;
    }

    public String getDeparture_city() {
        return departure_city;
    }

    public String getDestination_city() {
        return destination_city;
    }
}
