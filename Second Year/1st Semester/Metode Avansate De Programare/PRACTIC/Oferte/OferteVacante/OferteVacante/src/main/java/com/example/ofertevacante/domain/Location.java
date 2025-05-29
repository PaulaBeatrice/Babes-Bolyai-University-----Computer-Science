package com.example.ofertevacante.domain;

public class Location extends Entity<Double>{
    private String locationName;

    public Location(Double aDouble, String locationName) {
        super(aDouble);
        this.locationName = locationName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
