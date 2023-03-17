package com.example.zboruri.service;

import com.example.zboruri.domain.Client;
import com.example.zboruri.domain.Ticket;
import com.example.zboruri.domain.Zbor;
import com.example.zboruri.repository.Repository;

public class Service {
    private Repository<Integer, Client> clientRepo;
    private Repository<Long, Zbor> zborRepo;
    private Repository<Integer, Ticket> ticketRepo;

    public Service(Repository<Integer, Client> clientRepo, Repository<Long, Zbor> zborRepo, Repository<Integer, Ticket> ticketRepo) {
        this.clientRepo = clientRepo;
        this.zborRepo = zborRepo;
        this.ticketRepo = ticketRepo;
    }

    public Iterable<Client> getAllClienti(){
        return clientRepo.getAll();
    }

    public Iterable<Zbor> getAllZboruri(){
        return zborRepo.getAll();
    }

    public Iterable<Ticket> getAllTickete(){
        return ticketRepo.getAll();
    }

    public Ticket add(Ticket t){return ticketRepo.save(t);}

    public Zbor update(Zbor z){return zborRepo.update(z);}
}
