package repository.orm.hibernate;

import domain.Book;
import domain.Librarian;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import repository.IBookRepository;

import java.util.List;

public class BookRepository implements IBookRepository {

    public BookRepository(){}

    @Override
    public Book findOne(Integer integer) throws IllegalArgumentException {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        Book book = null;

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List<Book> books = session.createQuery("from Book", Book.class).list();
            for (Book bk : books) {
                if(bk.getId().equals(integer))
                    book = new Book(bk.getId(),bk.getTitle(),bk.getAuthor(),bk.getPublishing_house(),bk.getYear_of_publication());
            }
            session.getTransaction().commit();
        }

        if(book != null)
            System.out.println(book);
        else
            System.out.println("Book null!!");

        HibernateSession.close();
        return book;
    }

    @Override
    public List<Book> getAll() {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        try(Session  session = sessionFactory.openSession()){
            session.beginTransaction();
            List<Book> books = session.createQuery("from Book", Book.class).list();

            if (!(books).isEmpty()) {
                for (Book bk : books) {
                    if (bk != null) {
                        System.out.println(bk);
                    }
                }
            } else {
                System.out.println("Nu exista carti in baza de date.");
            }
            session.getTransaction().commit();
            HibernateSession.close();
            return books;
        }
    }

    @Override
    public void clear() {

    }

    @Override
    public void save(Book entity) {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(entity);
                tx.commit();
            } catch (RuntimeException ex) {
                HibernateSession.close();
                System.err.println("Eroare la inserare "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        HibernateSession.close();
    }

    @Override
    public void update(Book entity) {

    }

    @Override
    public void delete(Integer integer) {

    }
}
