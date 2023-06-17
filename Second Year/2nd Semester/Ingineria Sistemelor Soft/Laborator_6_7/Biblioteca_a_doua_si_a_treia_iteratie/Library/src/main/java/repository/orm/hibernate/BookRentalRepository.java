package repository.orm.hibernate;

import domain.Book;
import domain.BookRental;
import domain.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.IBookRentalRepository;

import java.util.List;

public class BookRentalRepository implements IBookRentalRepository {
    public BookRentalRepository(){}
    @Override
    public BookRental findOne(Integer integer) throws IllegalArgumentException {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        BookRental bookRental = null;

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List<BookRental> bookRentals = session.createQuery("from BookRental", BookRental.class).list();
            for (BookRental bk : bookRentals) {
                if(bk.getId().equals(integer))
                    bookRental = new BookRental(bk.getId(), bk.getClient(), bk.getBook(),bk.getReturned(), bk.getDays());
            }
            session.getTransaction().commit();
        }

        if(bookRental != null)
            System.out.println(bookRental);
        else
            System.out.println("Book null!!");

        HibernateSession.close();
        return bookRental;
    }

    @Override
    public List<BookRental> getAll() {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        try(Session  session = sessionFactory.openSession()){
            session.beginTransaction();
            List<BookRental> bookRentals = session.createQuery("from BookRental", BookRental.class).list();

            if (!(bookRentals).isEmpty()) {
                for (BookRental bk : bookRentals) {
                    if (bk != null) {
                        System.out.println(bk);
                    }
                }
            } else {
                System.out.println("Nu exista imprumuturi in baza de date.");
            }
            session.getTransaction().commit();
            HibernateSession.close();
            return bookRentals;
        }
    }

    @Override
    public void clear() {

    }

    @Override
    public void save(BookRental entity) {
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
    public void update(BookRental entity) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void returnBook(BookRental entity) {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();


        try(Session session = sessionFactory.openSession()){
            Transaction tx=null;
            try{
                tx = session.beginTransaction();
                BookRental bookRental = session.load( BookRental.class, entity.getId() );
                bookRental.setReturned(1);
                session.update(bookRental);
                tx.commit();

            } catch(RuntimeException ex){
                System.err.println("Eroare la update "+ex);
                if (tx!=null)
                    tx.rollback();
            }
        }

        HibernateSession.close();
    }
}
