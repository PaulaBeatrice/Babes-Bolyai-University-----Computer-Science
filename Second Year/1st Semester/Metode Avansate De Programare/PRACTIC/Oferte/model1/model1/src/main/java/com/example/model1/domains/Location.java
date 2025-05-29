package com.example.model1.domains;

public class Location extends Entity<Double>{
    private String locationName;

    public Location(Double locationId, String locationName) {
        super(locationId);
        this.locationName = locationName;
    }

    public String getLocationName() {
        return locationName;
    }
}
