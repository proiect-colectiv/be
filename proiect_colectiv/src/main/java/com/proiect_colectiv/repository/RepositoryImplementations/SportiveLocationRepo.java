package com.proiect_colectiv.repository.RepositoryImplementations;

import com.proiect_colectiv.model.Reservation;
import com.proiect_colectiv.model.SportiveLocation;
import com.proiect_colectiv.model.User;
import com.proiect_colectiv.repository.RepositoryInterfaces.ISportiveLocationRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
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
            } catch (Exception e) {
                e.printStackTrace();
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
        /*try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                //session.update(entity);
                session.createQuery("update SportiveLocation set name='"+entity.getName()+"', description='"+entity.getDescription()+"', rentPrice='"+entity.getRentPrice().toString()+"', openDays='"+entity.getOpenDays().size()+"'  where id="+entity.getID()).executeUpdate();
                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }*/
        try(Session session = sessionFactory.openSession()){
            Transaction tx=null;
            try{
                tx = session.beginTransaction();
                SportiveLocation sportiveLocation = (SportiveLocation) session.load( SportiveLocation.class, entity.getID());
                sportiveLocation.setRentPrice(entity.getRentPrice());
                sportiveLocation.setDescription(entity.getDescription());
                sportiveLocation.setCloseTime(entity.getCloseTime());
                sportiveLocation.setOpenTime(entity.getOpenTime());
                sportiveLocation.setOpenDays(entity.getOpenDays());
                tx.commit();
            } catch(RuntimeException ex){
                if (tx!=null)
                    tx.rollback();
            }
        }
    }
}
