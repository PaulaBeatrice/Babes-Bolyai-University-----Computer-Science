package com.example.anar.repository.factory;

import com.example.anar.domain.Localitate;
import com.example.anar.domain.Rau;
import com.example.anar.repository.LocalitateRepo;
import com.example.anar.repository.RauRepo;
import com.example.anar.repository.Repository;


public class RepositoryFactory {
    private static RepositoryFactory instance = new RepositoryFactory();

    private static Repository<Integer, Rau> createRauRepository() {
        return new RauRepo("jdbc:postgresql://localhost:5432/anar",
                "postgres", "postgress");
    }

    private static Repository<Integer, Localitate> createLocRepository() {
        return new LocalitateRepo("jdbc:postgresql://localhost:5432/anar",
                "postgres", "postgress");
    }


    public Repository createRepository(RepositoryEntity repositoryEntity) {
        switch (repositoryEntity) {
            case RAURI:
                return createRauRepository();
            case LOCALITATI:
                return createLocRepository();
                default:
                ;
        }
        return null;
    }

    /**
     * Method that retuns the instance of the repository factory
     * @return the repository factory
     */
    public static RepositoryFactory getInstance() {
        return instance;
    }
}
