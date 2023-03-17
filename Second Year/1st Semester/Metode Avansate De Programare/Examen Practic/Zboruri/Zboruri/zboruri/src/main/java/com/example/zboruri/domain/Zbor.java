package com.example.zboruri.domain;

import java.time.LocalDateTime;

public class Zbor extends Entity<Long>{
    private String from;
    private String to;
    private LocalDateTime departure_time;
    private LocalDateTime landing_time;
    private int seats;

    public Zbor(Long aLong, String from, String to, LocalDateTime departure_time, LocalDateTime landing_time, int seats) {
        super(aLong);
        this.from = from;
        this.to = to;
        this.departure_time = departure_time;
        this.landing_time = landing_time;
        this.seats = seats;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public LocalDateTime getDeparture_time() {
        return departure_time;
    }

    public LocalDateTime getLanding_time() {
        return landing_time;
    }

    public int getSeats() {
        return seats;
    }
}
