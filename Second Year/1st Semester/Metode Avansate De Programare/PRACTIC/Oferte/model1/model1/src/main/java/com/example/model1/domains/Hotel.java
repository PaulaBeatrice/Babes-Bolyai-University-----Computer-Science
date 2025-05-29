package com.example.model1.domains;

public class Hotel extends Entity<Double> {

    private Double hotelId;
    private Double locationId;
    private String hotelName;
    private int noRooms;
    private Double pricePerNight;

    private Type type;

    public Hotel(Double hotelId, Double locationId, String hotelName, int noRooms, Double pricePerNight, Type type) {
        super(hotelId);
        this.hotelId = hotelId;
        this.locationId = locationId;
        this.hotelName = hotelName;
        this.noRooms = noRooms;
        this.pricePerNight = pricePerNight;
        this.type = type;
    }

    public Double getLocationId() {
        return locationId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public int getNoRooms() {
        return noRooms;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }

    public Type getType() {
        return type;
    }

    public Double getHotelId() {
        return hotelId;
    }

    public void setHotelId(Double hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "locationId=" + locationId +
                ", hotelName='" + hotelName + '\'' +
                ", noRooms=" + noRooms +
                ", pricePerNight=" + pricePerNight +
                ", type=" + type +
                '}';
    }
}
