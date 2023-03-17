package com.example.ofertevacante.repository.factory;

import com.example.ofertevacante.domain.*;
import com.example.ofertevacante.repository.*;

import javax.xml.parsers.SAXParser;

public class RepositoryFactory {

    private static RepositoryFactory instance = new RepositoryFactory();

    private static Repository<Double, Location> createLocatiiRepository() {
        return new LocatiiRepository("jdbc:postgresql://localhost:5432/Oferte",
                "postgres", "postgress");
    }
    private static Repository<Double, Hotel> createHotelRepository() {
        return new HotelRepository("jdbc:postgresql://localhost:5432/Oferte",
                "postgres", "postgress");
    }
    private static Repository<Double, SpecialOffer> createOferteRepository() {
        return new OferteRepository("jdbc:postgresql://localhost:5432/Oferte",
                "postgres", "postgress");
    }

    private static Repository<Long, Client> createClientiRepository() {
        return new ClientRepository("jdbc:postgresql://localhost:5432/Oferte",
                "postgres", "postgress");
    }

    private static Repository<Double, Reservation> createReservationsRepository() {
        return new ReservationRepository("jdbc:postgresql://localhost:5432/Oferte",
                "postgres", "postgress");
    }

    public Repository createRepository(RepositoryEntity repositoryEntity) {
        switch (repositoryEntity) {
            case LOCATII:
                return createLocatiiRepository();
            case HOTELURI:
                return createHotelRepository();
            case OFERTE:
                return createOferteRepository();
            case CLIENTI:
                return createClientiRepository();
            case RESERVATIONS:
                return createReservationsRepository();
            default:
                return createLocatiiRepository();
        }
    }
    public static RepositoryFactory getInstance() {
        return instance;
    }
}
