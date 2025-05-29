package com.example.spital.service;

import com.example.spital.domain.Pacient;
import com.example.spital.domain.Pat;
import com.example.spital.repository.Repository;

public class Service {
    private Repository<Integer, Pat> patRepo;
    private Repository<String, Pacient> pacientRepo;

    public Service(Repository<Integer, Pat> patRepo, Repository<String, Pacient> pacientRepo) {
        this.patRepo = patRepo;
        this.pacientRepo = pacientRepo;
    }
    public Iterable<Pat> getAllPaturi(){return patRepo.getAll();}
    public Iterable<Pacient> getAllPacienti(){return pacientRepo.getAll();}

    public void updatePat(String val, Integer id){patRepo.update(val,id);}
}
