package com.example.login2.service;

import com.example.login2.domain.Nevoie;
import com.example.login2.domain.Persoana;
import com.example.login2.repository.Repository;

public class Service {
    private Repository<Integer, Persoana> persoaneRepository;
    private Repository<Integer, Nevoie> nevoiRepository;

    public Service(Repository<Integer, Persoana> persoaneRepository, Repository<Integer, Nevoie> nevoiRepository) {
        this.persoaneRepository = persoaneRepository;
        this.nevoiRepository = nevoiRepository;
    }


    public Persoana save(Persoana entity)
    {
        persoaneRepository.save(entity);
        return entity;
    }
    public Iterable<Nevoie> getAllNevoi() {
        return nevoiRepository.getAll();
    }
    public Iterable<Persoana> getAllPersoane() {
        return persoaneRepository.getAll();
    }

    public Nevoie updateNevoie(Nevoie n){return nevoiRepository.update(n);}

    public void add(Nevoie n){nevoiRepository.save(n);}
}
