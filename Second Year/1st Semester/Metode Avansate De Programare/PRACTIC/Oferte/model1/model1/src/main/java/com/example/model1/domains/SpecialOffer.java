package com.example.model1.domains;

import java.util.Date;

public class SpecialOffer extends Entity<Double> {

    private Double specialOfferId;
    private Double hotelId;
    private Date startDate;
    private Date endDate;
    private Integer percents;

    public SpecialOffer(Double specialOfferId, Double hotelId, Date startDate, Date endDate, Integer percents) {
        super(specialOfferId);
        this.specialOfferId = specialOfferId;
        this.hotelId = hotelId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percents = percents;
    }

    public Double getHotelId() {
        return hotelId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Integer getPercents() {
        return percents;
    }

    public Double getSpecialOfferId() {
        return specialOfferId;
    }

    public void setSpecialOfferId(Double specialOfferId) {
        this.specialOfferId = specialOfferId;
    }
}
