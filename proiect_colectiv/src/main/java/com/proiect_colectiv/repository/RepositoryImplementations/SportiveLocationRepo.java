package com.proiect_colectiv.repository.RepositoryImplementations;

import com.proiect_colectiv.model.Reservation;
import com.proiect_colectiv.model.SportiveLocation;
import com.proiect_colectiv.model.User;
import com.proiect_colectiv.repository.RepositoryInterfaces.ISportiveLocationRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class SportiveLocationRepo implements ISportiveLocationRepo {
    private SessionFactory sessionFactory;

    public SportiveLocationRepo() {
        this.sessionFactory = SessionFactoryClass.getSessionFactory();
    }

    @Override
    public SportiveLocation findOne(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx=null;
            try{
                tx = session.beginTransaction();
                SportiveLocation sportiveLocation= session.createQuery(" from SportiveLocation where id=:id", SportiveLocation.class)
                        .setParameter("id", id)
                        .setMaxResults(1)
                        .uniqueResult();
                tx.commit();
                return sportiveLocation;
            } catch(RuntimeException ex){
                if (tx!=null)
                    tx.rollback();
            }
        }
        return null;
    }

    @Override
    public Iterable<SportiveLocation> findAll() {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<SportiveLocation> list = session.createQuery("from SportiveLocation", SportiveLocation.class).list();
                tx.commit();
                return list;
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }

    @Override
    public void save(SportiveLocation entity) {
        try(Session session = sessionFactory.openSession()){
            Transaction tx=null;
            try{
                tx = session.beginTransaction();
                session.save(entity);
                tx.commit();
            }catch(RuntimeException ex){
                if (tx!=null)
                    tx.rollback();
            }
        }
    }

    @Override
    public void delete(Long id) {
        try(Session session = sessionFactory.openSession()){
            Transaction tx=null;
            try{
                tx = session.beginTransaction();
                SportiveLocation sportiveLocation= session.createQuery("from SportiveLocation where id=:id", SportiveLocation.class)
                        .setParameter("id", id)
                        .setMaxResults(1)
                        .uniqueResult();
                session.delete(sportiveLocation);
                tx.commit();
            } catch(RuntimeException ex){
                if (tx!=null)
                    tx.rollback();
            }
        }
    }

    @Override
    public void update(SportiveLocation entity) {

    }
}
