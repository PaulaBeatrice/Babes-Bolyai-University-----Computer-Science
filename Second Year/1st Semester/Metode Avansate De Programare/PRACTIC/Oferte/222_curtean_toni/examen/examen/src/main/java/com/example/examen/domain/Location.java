package com.example.examen.domain;

import java.util.Objects;

public class Location {

    private Double locationId;
    private String locationName;

    public Location(Double locationId, String locationName) {
        this.locationId = locationId;
        this.locationName = locationName;
    }

    public Double getLocationId() {
        return locationId;
    }

    public void setLocationId(Double locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return locationId.equals(location.locationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId);
    }
}
