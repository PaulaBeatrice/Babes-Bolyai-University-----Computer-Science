package com.example.examen.repository.factory;

import com.example.examen.domain.City;
import com.example.examen.domain.TrainStation;
import com.example.examen.repository.CityRepo;
import com.example.examen.repository.Repository;
import com.example.examen.repository.TrainStationRepo;

public class RepositoryFactory {

    private static RepositoryFactory instance = new RepositoryFactory();

    private static Repository<String , City> createCityRepository() {
        return new CityRepo("jdbc:postgresql://localhost:5432/examen",
                "postgres", "postgress");
    }

    private static Repository<String , TrainStation> createTrainStationRepository() {
        return new TrainStationRepo("jdbc:postgresql://localhost:5432/examen",
                "postgres", "postgress");
    }



    public Repository createRepository(RepositoryEntity repositoryEntity) {
        switch (repositoryEntity) {
            case CITIES:
                return createCityRepository();
            case TRAIN_STATIONS:
                return createTrainStationRepository();
            default:
                return createCityRepository();
        }
    }

    /**
     * Method that retuns the instance of the repository factory
     * @return the repository factory
     */
    public static RepositoryFactory getInstance() {
        return instance;
    }
}
