package com.proiect_colectiv.repository.RepositoryImplementations;

import com.proiect_colectiv.model.Reservation;
import com.proiect_colectiv.model.SportiveLocation;
import com.proiect_colectiv.model.User;
import com.proiect_colectiv.repository.RepositoryInterfaces.IReservationRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;


@Repository
public class ReservationRepo implements IReservationRepo {
    private SessionFactory sessionFactory;

    public ReservationRepo() {
        this.sessionFactory = SessionFactoryClass.getSessionFactory();
    }

    @Override
    public Reservation findOne(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx=null;
            try{
                tx = session.beginTransaction();
                Reservation reservation= session.createQuery(" from Reservation where id=:id", Reservation.class)
                        .setParameter("id", id)
                        .setMaxResults(1)
                        .uniqueResult();
                tx.commit();
                return reservation;
            } catch(RuntimeException ex){
                if (tx!=null)
                    tx.rollback();
            }
        }
        return null;
    }

    @Override
    public Iterable<Reservation> findAll() {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<Reservation> list = session.createQuery("from Reservation",Reservation.class).list();
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
    public void save(Reservation entity) {
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
                Reservation reservation= session.createQuery("from Reservation where id=:id", Reservation.class)
                        .setParameter("id", id)
                        .setMaxResults(1)
                        .uniqueResult();
                session.delete(reservation);
                tx.commit();
            } catch(RuntimeException ex){
                if (tx!=null)
                    tx.rollback();
            }
        }
    }

    @Override
    public void update(Reservation entity) {
        try(Session session = sessionFactory.openSession()){
            Transaction tx=null;
            try{
                tx = session.beginTransaction();
                Reservation reservation = (Reservation) session.load( Reservation.class, entity.getID());
                reservation.setCurrentNumberOfPlayers(entity.getCurrentNumberOfPlayers());
                tx.commit();
            } catch(RuntimeException ex){
                if (tx!=null)
                    tx.rollback();
            }
        }
    }

    @Override
    public Iterable<Reservation> filterReservationByLocation(Long locationID) {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<Reservation> list = session.createQuery("from Reservation R where R.reservedLocation.id=:reservedLocation",Reservation.class).setParameter("reservedLocation",locationID).list();
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
    public Iterable<Reservation> filterReservationByDay(LocalDate time) {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                LocalDateTime time1 = LocalDateTime.of(time.getYear(),time.getMonth(),time.getDayOfMonth(),0,0);
                LocalDateTime time2 = LocalDateTime.of(time.getYear(),time.getMonth(),time.getDayOfMonth(),23,59,59);
                tx = session.beginTransaction();
                List<Reservation> list = session.createQuery("from Reservation R where R.startTime>=:time1 and R.startTime<=:time2",Reservation.class)
                        .setParameter("time1",time1)
                        .setParameter("time2",time2)
                        .list();
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
    public Iterable<Reservation> getAllReservationsAfterDate(LocalDateTime time) {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<Reservation> list = session.createQuery("from Reservation R where R.startTime>:time",Reservation.class).setParameter("time",time).list();
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
    public Iterable<Reservation> getFutureReservations() {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<Reservation> list = session.createQuery("from Reservation R where R.endTime>:time order by startTime asc",Reservation.class).setParameter("time",LocalDateTime.now()).list();
                tx.commit();
                return list;
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }
}
