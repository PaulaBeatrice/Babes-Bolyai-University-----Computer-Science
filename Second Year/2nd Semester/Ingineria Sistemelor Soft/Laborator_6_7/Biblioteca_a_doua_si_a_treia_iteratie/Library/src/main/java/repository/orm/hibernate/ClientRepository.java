package repository.orm.hibernate;

import domain.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import repository.IClientRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ClientRepository implements IClientRepository {
    public ClientRepository(){}

    @Override
    public Client findByUserAndPass(String username, String password) {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        Client client = null;

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List<Client> clients = session.createQuery("from Client", Client.class).list();
            for (Client cl : clients) {
                if(cl.getUsername().equals(username) && cl.getPassword().equals(password))
                    client = new Client(cl.getId(),cl.getUsername(),cl.getPassword(),cl.getFirst_name(), cl.getLast_name(),cl.getEmail(),cl.getPhone_number());
            }
            session.getTransaction().commit();
        }

        if(client != null)
            System.out.println(client);
        else
            System.out.println("Client null!!");

        HibernateSession.close();
        return client;
    }

    @Override
    public Client findOne(Integer integer) throws IllegalArgumentException {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        Client client = null;

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List<Client> clients = session.createQuery("from Client", Client.class).list();
            for (Client cl : clients) {
                if(cl.getId().equals(integer))
                    client = new Client(cl.getId(),cl.getUsername(),cl.getPassword(),cl.getFirst_name(), cl.getLast_name(),cl.getEmail(),cl.getPhone_number());
            }
            session.getTransaction().commit();
        }

        if(client != null)
            System.out.println(client);
        else
            System.out.println("Client null!!");

        HibernateSession.close();
        return client;
    }

    @Override
    public List<Client> getAll() {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        try(Session  session = sessionFactory.openSession()){
            session.beginTransaction();
            List<Client> clients = session.createQuery("from Client", Client.class).list();

            if (!(clients).isEmpty()) {
                for (Client cl : clients) {
                    if (cl != null) {
                        System.out.println(cl);
                    }
                }
            } else {
                System.out.println("Nu exista clienti in baza de date.");
            }
            session.getTransaction().commit();
            HibernateSession.close();
            return clients;
        }
    }

    @Override
    public void clear() {

    }

    @Override
    public void save(Client entity) {
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
    public void update(Client entity) {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();


        try(Session session = sessionFactory.openSession()){
            Transaction tx=null;
            try{
                tx = session.beginTransaction();
                Client client = session.load( Client.class, entity.getId() );
                System.out.println("------ID REPO UPDATE ------------"+ entity.getId());
                client.setFirst_name(entity.getFirst_name());
                client.setLast_name(entity.getLast_name());
                client.setEmail(entity.getEmail());
                client.setPhone_number(entity.getPhone_number());
                client.setUsername(entity.getUsername());
                client.setPassword(entity.getPassword());

                session.update(client);
                tx.commit();

            } catch(RuntimeException ex){
                System.err.println("Eroare la update "+ex);
                if (tx!=null)
                    tx.rollback();
            }
        }

        HibernateSession.close();
    }

    @Override
    public void delete(Integer integer) {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                Client crit = session.createQuery("from Client where id = :clientId", Client.class)
                        .setParameter("clientId", integer)
                        .setMaxResults(1)
                        .uniqueResult();
                System.err.println("Stergem clientul " + crit.getId());
                session.delete(crit);
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Eroare la stergere "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        HibernateSession.close();
    }
}
