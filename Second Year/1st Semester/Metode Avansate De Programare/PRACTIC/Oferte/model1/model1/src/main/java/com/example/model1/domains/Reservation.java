package com.example.model1.domains;

import java.util.Date;

public class Reservation extends Entity<Double> {
    private Double reservationId;
    private Double clientId;
    private Double hotelId;
    private Date startDate;
    private Integer numberOfNights;

    public Reservation(Double reservationId, Double clientId, Double hotelId, Date startDate, Integer numberOfNights) {
        super(reservationId);
        this.reservationId = reservationId;
        this.clientId = clientId;
        this.hotelId = hotelId;
        this.startDate = startDate;
        this.numberOfNights = numberOfNights;
    }

    public Double getReservationId() {
        return reservationId;
    }

    public void setReservationId(Double reservationId) {
        this.reservationId = reservationId;
    }

    public Double getClientId() {
        return clientId;
    }

    public void setClientId(Double clientId) {
        this.clientId = clientId;
    }

    public Double getHotelId() {
        return hotelId;
    }

    public void setHotelId(Double hotelId) {
        this.hotelId = hotelId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(Integer numberOfNights) {
        this.numberOfNights = numberOfNights;
    }
}
