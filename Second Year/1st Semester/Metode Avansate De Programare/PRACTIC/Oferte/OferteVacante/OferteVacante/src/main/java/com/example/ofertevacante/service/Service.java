package com.example.ofertevacante.service;


import com.example.ofertevacante.domain.Hotel;
import com.example.ofertevacante.domain.Location;
import com.example.ofertevacante.domain.SpecialOffer;
import com.example.ofertevacante.repository.Repository;

public class Service {
    private Repository<Double, Location> locatiiRepository;
    private Repository<Double, Hotel> hotelRepository;
    private Repository<Double, SpecialOffer> OferteRepository;

    public Service(Repository<Double, Location> locatiiRepository, Repository<Double, Hotel> hotelRepository, Repository<Double, SpecialOffer> oferteRepository) {
        this.locatiiRepository = locatiiRepository;
        this.hotelRepository = hotelRepository;
        this.OferteRepository = oferteRepository;
    }

    public Iterable<SpecialOffer> getAllOferte() {
        return OferteRepository.getAll();
    }
    public Iterable<Location> getAllLocatii() {
        return locatiiRepository.getAll();
    }
    public Iterable<Hotel> getAllHoteluri() {
        return hotelRepository.getAll();
    }
}
