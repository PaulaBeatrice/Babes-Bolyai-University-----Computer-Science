package com.example.ofertevacante.domain;

import java.time.LocalDateTime;

public class SpecialOffer extends Entity<Double>{
    private Double hotel_id;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private Integer percent;

    public SpecialOffer(Double aDouble, Double hotel_id, LocalDateTime start_date, LocalDateTime end_date, Integer percent) {
        super(aDouble);
        this.hotel_id = hotel_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.percent = percent;
    }

    public Double getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(Double hotel_id) {
        this.hotel_id = hotel_id;
    }

    public LocalDateTime getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDateTime start_date) {
        this.start_date = start_date;
    }

    public LocalDateTime getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDateTime end_date) {
        this.end_date = end_date;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    @Override
    public String toString() {
        return "SpecialOffer{" +
                "hotel_id=" + hotel_id +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", percent=" + percent +
                '}';
    }
}
