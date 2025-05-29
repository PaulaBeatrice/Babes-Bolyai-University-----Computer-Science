package com.example.ofertevacante.repository.factory;

import com.example.ofertevacante.domain.Hotel;
import com.example.ofertevacante.domain.Location;
import com.example.ofertevacante.domain.SpecialOffer;
import com.example.ofertevacante.repository.HotelRepository;
import com.example.ofertevacante.repository.LocatiiRepository;
import com.example.ofertevacante.repository.OferteRepository;
import com.example.ofertevacante.repository.Repository;

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

    public Repository createRepository(RepositoryEntity repositoryEntity) {
        switch (repositoryEntity) {
            case LOCATII:
                return createLocatiiRepository();
            case HOTELURI:
                return createHotelRepository();
            case OFERTE:
                return createOferteRepository();
            default:
                return createLocatiiRepository();
        }
    }
    public static RepositoryFactory getInstance() {
        return instance;
    }
}
