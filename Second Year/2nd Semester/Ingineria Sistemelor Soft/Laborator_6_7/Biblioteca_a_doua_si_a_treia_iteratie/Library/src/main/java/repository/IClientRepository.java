package repository;

import domain.Client;
import domain.Librarian;

public interface IClientRepository extends IRepository<Integer, Client>{
    Client findByUserAndPass(String username, String password);
}
