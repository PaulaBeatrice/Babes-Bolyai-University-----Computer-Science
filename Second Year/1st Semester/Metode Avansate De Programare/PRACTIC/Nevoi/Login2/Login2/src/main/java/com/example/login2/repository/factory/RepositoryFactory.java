package com.example.login2.repository.factory;


import com.example.login2.domain.Nevoie;
import com.example.login2.domain.Persoana;
import com.example.login2.repository.NevoiRepository;
import com.example.login2.repository.PersoaneRepository;
import com.example.login2.repository.Repository;

public class RepositoryFactory {

    private static RepositoryFactory instance = new RepositoryFactory();

    private static Repository<Integer, Persoana> createPersoaneRepository() {
        return new PersoaneRepository("jdbc:postgresql://localhost:5432/Login",
                "postgres", "postgress");
    }
    private static Repository<Integer, Nevoie> createNevoiRepository() {
        return new NevoiRepository("jdbc:postgresql://localhost:5432/Login",
                "postgres", "postgress");
    }

    public Repository createRepository(RepositoryEntity repositoryEntity) {
        switch (repositoryEntity) {
            case PERSOANE:
                return createPersoaneRepository();
            case NEVOI:
                return createNevoiRepository();
            default:
                return createPersoaneRepository();
        }
    }
    public static RepositoryFactory getInstance() {
        return instance;
    }
}
