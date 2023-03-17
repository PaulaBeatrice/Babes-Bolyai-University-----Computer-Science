package com.example.clinica.service;

import com.example.clinica.domain.Consultatie;
import com.example.clinica.domain.Medic;
import com.example.clinica.domain.Sectie;
import com.example.clinica.repository.Repository;


public class Service {
    private Repository<Integer, Sectie> sectieDbRepo;
    private Repository<Integer, Medic> medicRepo;
    private Repository<Integer, Consultatie> consultatieRepo;

    public Service(Repository<Integer, Sectie> sectieDbRepo, Repository<Integer, Medic> medicRepo, Repository<Integer, Consultatie> consultatieRepo) {
        this.sectieDbRepo = sectieDbRepo;
        this.medicRepo = medicRepo;
        this.consultatieRepo = consultatieRepo;
    }

    public Iterable<Sectie> getAllSectii(){
        return sectieDbRepo.getAll();
    }

    public Iterable<Medic> getAllMedici(){
        return medicRepo.getAll();
    }

    public Iterable<Consultatie> getAllConsultatii(){return consultatieRepo.getAll();}

    public Consultatie add(Consultatie c){return consultatieRepo.save(c);}

}
