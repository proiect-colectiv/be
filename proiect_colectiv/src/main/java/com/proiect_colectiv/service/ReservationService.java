package com.proiect_colectiv.service;

import com.proiect_colectiv.model.DTO.FilterDTO;
import com.proiect_colectiv.model.Reservation;
import com.proiect_colectiv.repository.RepositoryInterfaces.IReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class ReservationService implements IReservationService {

    @Autowired
    public IReservationRepo reservationRepo;

    public ReservationService(IReservationRepo reservationRepo) {
        this.reservationRepo = reservationRepo;
    }

    @Override
    public Reservation findOne(Long id) {
        return reservationRepo.findOne(id);
    }

    @Override
    public Iterable<Reservation> findAll() {
        return reservationRepo.findAll();
    }

    @Override
    public void save(Reservation entity) {
        reservationRepo.save(entity);
    }

    @Override
    public void delete(Long id) {
        reservationRepo.delete(id);
    }

    @Override
    public void update(Reservation entity) {
        reservationRepo.update(entity);
    }

    @Override
    public Iterable<Reservation> filterReservationByLocation(Long locationID) {
        return reservationRepo.filterReservationByLocation(locationID);
    }

    @Override
    public Iterable<Reservation> filterReservationByDay(LocalDate time) {
        return reservationRepo.filterReservationByDay(time);
    }

    @Override
    public Iterable<Reservation> getAllReservationsAfterDate(LocalDateTime time) {
        return reservationRepo.getAllReservationsAfterDate(time);
    }

    @Override
    public Iterable<Reservation> getFutureReservations() {
        return reservationRepo.getFutureReservations();
    }

    @Override
    public Iterable<Reservation> getReservationsFiltered(FilterDTO filter) {
        if (filter != null) {
            List<Reservation> filteredReservations = new ArrayList<>();
            getFutureReservations().forEach((Reservation res) -> {
                        boolean matches = true;
                        //filter by location
                        if (filter.getLocationId() != null) {
                            if(filter.getLocationId() != res.reservedLocation.getID())
                                matches = false;
                        }
                        //filter by currentPlayerNumber
                        if(filter.getPlayerNumber() != null){
                            if(filter.getPlayerNumber() != res.getCurrentNumberOfPlayers())
                                matches = false;
                        }
                        //TODO: filter by date too
                        if(matches){
                            filteredReservations.add(res);
                        }
                    }
            );
            return filteredReservations;
        }
        return getFutureReservations();
    }
}
