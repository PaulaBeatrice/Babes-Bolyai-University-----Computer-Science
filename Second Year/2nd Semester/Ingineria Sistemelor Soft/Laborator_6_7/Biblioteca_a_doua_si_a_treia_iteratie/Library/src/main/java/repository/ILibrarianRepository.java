package repository;

import domain.Librarian;

public interface ILibrarianRepository extends IRepository<Integer, Librarian>{
    Librarian findByUserAndPass(String username, String password);
}
