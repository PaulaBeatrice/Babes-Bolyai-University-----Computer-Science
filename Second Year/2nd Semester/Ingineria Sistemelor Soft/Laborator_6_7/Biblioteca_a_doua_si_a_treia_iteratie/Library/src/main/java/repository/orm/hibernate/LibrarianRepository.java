package repository.orm.hibernate;

import domain.Librarian;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import repository.ILibrarianRepository;

import java.util.List;

public class LibrarianRepository implements ILibrarianRepository {

    public LibrarianRepository(){
    }

    @Override
    public Librarian findByUserAndPass(String username, String password) {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        Librarian librarian = null;

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List<Librarian> librarians = session.createQuery("from Librarian", Librarian.class).list();
            for (Librarian libr : librarians) {
                if(libr.getUsername().equals(username) && libr.getPassword().equals(password))
                    librarian = new Librarian(libr.getId(),libr.getUsername(),libr.getPassword(),libr.getFirst_name(), libr.getLast_name(),libr.getEmail());
            }
            session.getTransaction().commit();
        }

        if(librarian != null)
            System.out.println(librarian);
        else
            System.out.println("Librarian null!!");

        HibernateSession.close();
        return librarian;
    }

    @Override
    public Librarian findOne(Integer integer) throws IllegalArgumentException {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        Librarian librarian = null;

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List<Librarian> librarians = session.createQuery("from Librarian", Librarian.class).list();
            for (Librarian libr : librarians) {
                if(libr.getId().equals(integer))
                    librarian = new Librarian(libr.getId(),libr.getUsername(),libr.getPassword(),libr.getFirst_name(), libr.getLast_name(),libr.getEmail());
            }
            session.getTransaction().commit();
        }

        if(librarian != null)
            System.out.println(librarian);
        else
            System.out.println("Librarian null!!");
        HibernateSession.close();
        return librarian;
    }

    @Override
    public List<Librarian> getAll() {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        try(Session  session = sessionFactory.openSession()){
            session.beginTransaction();
            List<Librarian> librarians = session.createQuery("from Librarian", Librarian.class).list();

            if (!(librarians).isEmpty()) {
                for (Librarian libr : librarians) {
                    if (libr != null) {
                        System.out.println(libr);
                    }
                }
            } else {
                System.out.println("Nu exista bibliotecari in baza de date.");
            }
            session.getTransaction().commit();
            HibernateSession.close();
            return librarians;
        }
    }

    @Override
    public void clear() {

    }

    @Override
    public void save(Librarian entity) {

    }

    @Override
    public void update(Librarian entity) {

    }

    @Override
    public void delete(Integer integer) {

    }
}
