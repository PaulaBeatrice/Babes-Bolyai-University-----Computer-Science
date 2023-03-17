package com.example.clinica.repository.factory;

import com.example.clinica.domain.Consultatie;
import com.example.clinica.domain.Medic;
import com.example.clinica.domain.Sectie;
import com.example.clinica.repository.ConsultatieDbRepo;
import com.example.clinica.repository.MedicDbRepo;
import com.example.clinica.repository.Repository;
import com.example.clinica.repository.SectieDbRepo;

public class RepositoryFactory {
    private static RepositoryFactory instance = new RepositoryFactory();

    private static Repository<Integer, Sectie> createSectiiRepository() {
        return new SectieDbRepo("jdbc:postgresql://localhost:5432/clinica",
                "postgres", "postgress");
    }

    private static Repository<Integer, Medic> createMediciRepository() {
        return new MedicDbRepo("jdbc:postgresql://localhost:5432/clinica",
                "postgres", "postgress");
    }

    private static Repository<Integer, Consultatie> createConsultatiiRepository(){
        return new ConsultatieDbRepo("jdbc:postgresql://localhost:5432/clinica",
                "postgres", "postgress");
    }


    public Repository createRepository(RepositoryEntity repositoryEntity) {
        switch (repositoryEntity) {
            case SECTIE:
                return createSectiiRepository();
            case MEDIC:
                return createMediciRepository();
            case CONSULTATIE:
                return createConsultatiiRepository();
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
