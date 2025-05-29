package com.example.model1.service;

import com.example.model1.domains.*;
import com.example.model1.events.HotelChangeEvent;
import com.example.model1.observer.Observable;
import com.example.model1.observer.Observer;
import com.example.model1.repos.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Service implements Observable<HotelChangeEvent> {
    private Repository<Double, Location> locationRepository;
    private Repository<Double, Hotel> hotelRepository;

    private Repository<Double, SpecialOffer> specialOfferRepository;

    private Repository<Long, Client> clientRepository;
    private Repository<Double, Reservation> reservationRepository;

    private List<Observer<HotelChangeEvent>> observerList = new ArrayList<>();

    public Service(Repository<Double, Location> locationRepository, Repository<Double, Hotel> hotelRepository, Repository<Double, SpecialOffer> specialOfferRepository, Repository<Long, Client> clientRepository, Repository<Double, Reservation> repositoryRepository) {
        this.locationRepository = locationRepository;
        this.hotelRepository = hotelRepository;
        this.specialOfferRepository = specialOfferRepository;
        this.clientRepository = clientRepository;
        this.reservationRepository = repositoryRepository;
    }

    @Override
    public void addObserver(Observer<HotelChangeEvent> e) {
        observerList.add(e);
    }

    @Override
    public void removeObserver(Observer<HotelChangeEvent> e) {
        observerList.remove(e);
    }

    @Override
    public void notifyObservers(HotelChangeEvent t) {
        observerList.forEach(x -> x.update(t));
    }

    public Double findALocationIdByName(String name) {
        return locationRepository.findOneByName(name);
    }

    public List<String> locationNames() {
        return locationRepository.getAll();
    }

    public List<Hotel> findAllByLocation(String name) {
        return (List<Hotel>) hotelRepository.findAllWithName(name);
    }

    public List<SpecialOffer> specialOffers(Double hotelId, Date startDate, Date endDate) {
        return specialOfferRepository.findOne(hotelId, startDate, endDate);
    }

    public Hotel findHotel(Double id) {
        return hotelRepository.findEntity(id);
    }

    public Location findLocation(Double Id) {
        return locationRepository.findEntity(Id);
    }

    public Client findClient(Long id) {
        return clientRepository.findEntity(id);
    }

    public List<SpecialOffer> offers(Date date, Integer fidelityGrade) {
        return specialOfferRepository.offers(date, fidelityGrade);
    }

    public Double findHotelByName(String name) {
        return hotelRepository.findOneByName(name);
    }

    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.add(reservation);
    }

    public List<Reservation> getAll() {
        return reservationRepository.getAllEntitys();
    }


}
