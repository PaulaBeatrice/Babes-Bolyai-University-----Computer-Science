package service;

import domain.*;
import repository.IBookRentalRepository;
import repository.IBookRepository;
import repository.IClientRepository;
import repository.ILibrarianRepository;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private IBookRepository bookRepository;
    private IClientRepository clientRepository;
    private ILibrarianRepository librarianRepository;
    private IBookRentalRepository bookRentalRepository;

    public Service(IBookRepository bookRepository, IClientRepository clientRepository, ILibrarianRepository librarianRepository, IBookRentalRepository bookRentalRepository) {
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
        this.librarianRepository = librarianRepository;
        this.bookRentalRepository = bookRentalRepository;
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
    public List<Client> getAllClients(){
        return clientRepository.getAll();
    }

    public void addClient(Client client){clientRepository.save(client);}

    public void deleteClient(int id){clientRepository.delete(id);}

    public void updateClient(Client client){clientRepository.update(client);}

    public void deleteBook(int id){bookRepository.delete(id);}


    public void borrowBook(BookRental bookRental){bookRentalRepository.save(bookRental);}
    public void returnBook(Book book, Client client)
    {
        for(BookRental bookRental: bookRentalRepository.getAll())
            if(bookRental.getBook() == book.getId() && bookRental.getClient() == client.getId())
                bookRentalRepository.returnBook(bookRental);
    }

    public List<Book> getNotRentedBooks(){
        List<Book> books = new ArrayList<Book>();
        for(Book b: bookRepository.getAll()){
            int ok = 1;
            for(BookRental bookRental: bookRentalRepository.getAll()){
                if(bookRental.getBook() == b.getId() && bookRental.getReturned() == 0)
                    ok = 0;
            }
//            System.err.println(ok);
            if(ok == 1)
                books.add(b);
        }

        return books;
    }

    public Book getBook(int id){return bookRepository.findOne(id);}

    public List<BookRental> getRentalHistory(Client client){
        List<BookRental> bookRentals = new ArrayList<BookRental>();
        for(BookRental bookRental: bookRentalRepository.getAll())
            if(bookRental.getClient() == client.getId())
                bookRentals.add(bookRental);
        return bookRentals;
    }

    public List<Book> getRentedBooks(Client client){
        List<Book> books = new ArrayList<Book>();
        for(BookRental bookRental: bookRentalRepository.getAll())
            if(bookRental.getClient() == client.getId() && bookRental.getReturned()==0){
                Book book = getBook(bookRental.getBook());
                books.add(book);
            }
        return books;
    }
}
