package com.example.zboruri.repository.factory;

import com.example.zboruri.domain.Client;
import com.example.zboruri.domain.Ticket;
import com.example.zboruri.domain.Zbor;
import com.example.zboruri.repository.ClientiRepo;
import com.example.zboruri.repository.Repository;
import com.example.zboruri.repository.TicketRepo;
import com.example.zboruri.repository.ZborRepo;

public class RepositoryFactory {

    private static RepositoryFactory instance = new RepositoryFactory();

    private static Repository<Integer, Client> createClientRepository() {
        return new ClientiRepo("jdbc:postgresql://localhost:5432/zbor",
                "postgres", "postgress");
    }

    private static Repository<Long, Zbor> createZborRepository() {
        return new ZborRepo("jdbc:postgresql://localhost:5432/zbor",
                "postgres", "postgress");
    }

    private static Repository<Integer, Ticket> createTicketeRepository() {
        return new TicketRepo("jdbc:postgresql://localhost:5432/zbor",
                "postgres", "postgress");
    }

    public Repository createRepository(RepositoryEntity repositoryEntity) {
        switch (repositoryEntity) {
            case CLIENTI:
                return createClientRepository();
            case ZBORURI:
                return createZborRepository();
            case TICKETE:
                return createTicketeRepository();
            default:
                return createClientRepository();
        }
    }
    public static RepositoryFactory getInstance() {
        return instance;
    }
}
