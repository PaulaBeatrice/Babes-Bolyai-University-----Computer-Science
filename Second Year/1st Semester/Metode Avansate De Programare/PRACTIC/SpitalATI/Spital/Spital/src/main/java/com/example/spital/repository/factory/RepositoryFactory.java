package com.example.spital.repository.factory;

import com.example.spital.domain.Pacient;
import com.example.spital.domain.Pat;
import com.example.spital.repository.PacientRepo;
import com.example.spital.repository.PatRepo;
import com.example.spital.repository.Repository;

public class RepositoryFactory {
    private static RepositoryFactory instance = new RepositoryFactory();

    private static Repository<Integer, Pat> createPatRepository() {
        return new PatRepo("jdbc:postgresql://localhost:5432/spital",
                "postgres", "postgress");
    }

    private static Repository<String, Pacient> createPacientRepository() {
        return new PacientRepo("jdbc:postgresql://localhost:5432/spital",
                "postgres", "postgress");
    }


    public Repository createRepository(RepositoryEntity repositoryEntity) {
        switch (repositoryEntity) {
            case PAT:
                return createPatRepository();
            case PACIENT:
                return createPacientRepository();
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
