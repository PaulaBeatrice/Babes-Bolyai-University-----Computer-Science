package com.example.anar.service;

import com.example.anar.domain.Localitate;
import com.example.anar.domain.Rau;
import com.example.anar.repository.Repository;

public class Service {
    private Repository<Integer, Rau> rauRepo;
    private Repository<Integer, Localitate> locRepo;

    public Service(Repository<Integer, Rau> rauRepo, Repository<Integer, Localitate> locRepo) {
        this.rauRepo = rauRepo;
        this.locRepo = locRepo;
    }

    public Iterable<Rau> getAllRauri(){return rauRepo.getAll();}

    public Iterable<Localitate> getAllLocalitati(){return locRepo.getAll();}

    public void modificaCotaRau(int idRau, int newCota){
        rauRepo.update(idRau,newCota);
    }

}
