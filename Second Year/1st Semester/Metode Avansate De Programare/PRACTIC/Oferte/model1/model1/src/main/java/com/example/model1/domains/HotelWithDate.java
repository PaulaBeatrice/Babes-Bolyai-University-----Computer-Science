package com.example.model1.domains;

import java.util.Date;

public class HotelWithDate {
    private String hotelName;
    private String LocationName;
    private Date startDate;
    private Date endDate;

    public HotelWithDate(String hotelName, String locationName, Date startDate, Date endDate) {
        this.hotelName = hotelName;
        LocationName = locationName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getLocationName() {
        return LocationName;
    }

    public void setLocationName(String locationName) {
        LocationName = locationName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
