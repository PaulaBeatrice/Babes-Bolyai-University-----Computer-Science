package com.example.ofertevacante.service;


import com.example.ofertevacante.domain.*;
import com.example.ofertevacante.repository.Repository;

public class Service {
    private Repository<Double, Location> locatiiRepository;
    private Repository<Double, Hotel> hotelRepository;
    private Repository<Double, SpecialOffer> oferteRepository;
    private Repository<Long, Client> clientRepository;

    private Repository<Double, Reservation> reservationRepository;

    public Service(Repository<Double, Location> locatiiRepository, Repository<Double, Hotel> hotelRepository, Repository<Double, SpecialOffer> oferteRepository, Repository<Long, Client> clientRepository, Repository<Double, Reservation> reservationRepository) {
        this.locatiiRepository = locatiiRepository;
        this.hotelRepository = hotelRepository;
        this.oferteRepository = oferteRepository;
        this.clientRepository = clientRepository;
        this.reservationRepository = reservationRepository;
    }

    public Iterable<SpecialOffer> getAllOferte() {
        return oferteRepository.getAll();
    }
    public Iterable<Location> getAllLocatii() {
        return locatiiRepository.getAll();
    }
    public Iterable<Hotel> getAllHoteluri() {
        return hotelRepository.getAll();
    }

    public Iterable<Client> getAllClienti(){ return clientRepository.getAll();}

    public Iterable<Reservation> getAllReservations(){return reservationRepository.getAll();}
    public void add(Reservation r){ reservationRepository.save(r);}

}
