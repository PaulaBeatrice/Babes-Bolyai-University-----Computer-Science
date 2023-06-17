package service;

import domain.Book;
import domain.Client;
import domain.Librarian;
import domain.User;
import repository.IBookRepository;
import repository.IClientRepository;
import repository.ILibrarianRepository;

import java.util.List;

public class Service {
    private IBookRepository bookRepository;
    private IClientRepository clientRepository;
    private ILibrarianRepository librarianRepository;

    public Service(IBookRepository bookRepository, IClientRepository clientRepository, ILibrarianRepository librarianRepository) {
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
        this.librarianRepository = librarianRepository;
    }

    public User findUser(String username, String password){
        Client cl = clientRepository.findByUserAndPass(username,password);
        if(cl != null){
            return cl;
        }
        Librarian lr = librarianRepository.findByUserAndPass(username,password);
        if(lr != null){
            return lr;
        }
        return null;
    }

    public void addBook(Book book){
        bookRepository.save(book);
    }

    public List<Book> getAllBooks(){
        return bookRepository.getAll();
    }


//    public int getType(User user){
//        Client cl = clientRepository.findByUserAndPass(username,password);
//        Librarian lr = librarianRepository.findByUserAndPass(username,password);
//        if(cl == null)
//            return 1; // bibliotecar
//        else
//            return 0; // client
//    }
}
