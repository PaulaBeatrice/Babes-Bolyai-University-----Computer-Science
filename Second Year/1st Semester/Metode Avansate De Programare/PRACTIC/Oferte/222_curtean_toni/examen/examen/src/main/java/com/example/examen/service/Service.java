package com.example.examen.service;

import com.example.examen.domain.Hotel;
import com.example.examen.domain.Location;
import com.example.examen.domain.SpecialOffer;
import com.example.examen.repository.RepositoryHotel;
import com.example.examen.repository.RepositoryLocation;
import com.example.examen.repository.RepositorySpecialOffer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Service {

    private RepositoryHotel repositoryHotel;

    private RepositoryLocation repositoryLocation;

    private RepositorySpecialOffer repositorySpecialOffer;

    public Service(RepositoryHotel repositoryHotel, RepositoryLocation repositoryLocation,RepositorySpecialOffer repositorySpecialOffer) {
        this.repositoryHotel = repositoryHotel;
        this.repositoryLocation = repositoryLocation;
        this.repositorySpecialOffer=repositorySpecialOffer;
    }

    public List<Hotel> getHotels(){
        return this.repositoryHotel.getEntities();
    }

    public List<Location> getLocations(){
        return this.repositoryLocation.getEntities();
    }

    public Location getLocationByName(String name){
        for(Location location:repositoryLocation.getEntities())
            if(Objects.equals(location.getLocationName(), name))
                return location;
        return null;
    }

    public List<Hotel> getHotelsForLocation(Double locationId) {
        List<Hotel> requested=new ArrayList<>();
        for(Hotel hotel: repositoryHotel.getEntities())
            if(Objects.equals(hotel.getLocationId(), locationId))
                requested.add(hotel);
        return requested;
    }

    public List<SpecialOffer> getOffers(Hotel hotel, LocalDate startDate, LocalDate endDate) {
        List<SpecialOffer> requested = new ArrayList<>();
        for (SpecialOffer specialOffer : repositorySpecialOffer.getEntities()){
            if (Objects.equals(specialOffer.getHotelId(), hotel.getHotelId()) && specialOffer.getStartDate().toString().equals(startDate.toString()) && specialOffer.getEndDate().toString().equals(endDate.toString())) {
                requested.add(specialOffer);
            }
        }
        return requested;
    }
}
