package repository;

import domain.Book;
import domain.Client;
import domain.Librarian;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import repository.orm.hibernate.ClientRepository;

import java.util.List;

public class MainDeTest {
    private static SessionFactory sessionFactory;


    static void close() {
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }

    public static void main(String ... arg) {
        // initializare
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml") // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch(Exception e){
            StandardServiceRegistryBuilder.destroy(registry);
            throw new RuntimeException(e);
        }

//        Librarian librarian = new Librarian(0,"CCCCCCCCCCC","B","B","A","A");
//        Client cl = new Client(0,"C","P","P","P","P","P");
        Book bk = new Book(0,"A","A","A",2000);
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(bk);
            session.getTransaction().commit();
        }
//        ClientRepository clientRepository = new ClientRepository();
//        clientRepository.save(cl);

//        List<Librarian> librarians;

//        try(Session  session = sessionFactory.openSession()){
//            session.beginTransaction();
//            List<Librarian> librarians = session.createQuery("from Librarian", Librarian.class).list();
//
//            if (!(librarians).isEmpty()) {
//                for (Librarian libr : librarians) {
//                    if (libr != null) {
//                        System.out.println(libr);
//                    }
//                }
//            } else {
//                System.out.println("Nu exista bibliotecari in baza de date.");
//            }
//            session.getTransaction().commit();
//        }
//        try(Session  session = sessionFactory.openSession()){
//            session.beginTransaction();
//            List<Client> clients = session.createQuery("from Client", Client.class).list();
//
//            if (!(clients).isEmpty()) {
//                for (Client cl2 : clients) {
//                    if (cl2 != null) {
//                        System.out.println(cl2);
//                    }
//                }
//            } else {
//                System.out.println("Nu exista clienti in baza de date.");
//            }
//            session.getTransaction().commit();
//        }
        try(Session  session = sessionFactory.openSession()){
            session.beginTransaction();
            List<Book> books = session.createQuery("from Book", Book.class).list();

            if (!(books).isEmpty()) {
                for (Book b : books) {
                    if (b != null) {
                        System.out.println(bk);
                    }
                }
            } else {
                System.out.println("Nu exista carti in baza de date.");
            }
            session.getTransaction().commit();
        }

        close();
    }
}
