package com.proiect_colectiv.controller;


import com.proiect_colectiv.model.Day;
import com.proiect_colectiv.model.Reservation;
import com.proiect_colectiv.model.SportiveLocation;
import com.proiect_colectiv.model.User;
import com.proiect_colectiv.repository.RepositoryInterfaces.IReservationRepo;
import com.proiect_colectiv.repository.RepositoryInterfaces.ISportiveLocationRepo;
import com.proiect_colectiv.service.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@CrossOrigin
@RestController()
public class Controller {


//    @Autowired
//    IReservationService reservationService;


    //shortcut to data :))
    @Autowired
    IReservationRepo reservationRepo;

    @Autowired
    ISportiveLocationRepo locationRepo;

    //placeholder list until the flow of data from DB -> repo-> service is ready to use
//    static List<SportiveLocation> replacementList = new ArrayList<>();
//    static Set<Day> allDays = new HashSet<>(Arrays.asList(Day.values()));
//    static{
//        replacementList.add(new SportiveLocation(1l,"Baza Sportiva Gheorgheni","Strada Alexandru Vaida Voevod, Cluj-Napoca","Centru sportiv cu de toate pentru toti.",120d, LocalTime.of(6,0),LocalTime.of(23,00), allDays));
//        replacementList.add(new SportiveLocation(2l,"Baza Test 2","Strada test 2","Descriere test 2",120d, LocalTime.of(6,0),LocalTime.of(22,00), allDays));
//        replacementList.add(new SportiveLocation(3l,"Baza Test 3","Strada test 3","Descriere test 3",150d, LocalTime.of(7,0),LocalTime.of(22,00), allDays));
//        replacementList.add(new SportiveLocation(4l,"Baza Test 4","Strada test 4","Descriere test 4",45d, LocalTime.of(8,0),LocalTime.of(22,00), allDays));
//        replacementList.add(new SportiveLocation(5l,"Baza Test 5","Strada test 5","Descriere test 5",12d, LocalTime.of(10,0),LocalTime.of(22,00), allDays));
//    }

    /**
     *
     *      url  :   http://localhost:8080/proiectcolectiv/sportivelocations
     *
     * @return the list containing all SportiveLocations that exists, in JSON format
     */

    @GetMapping(path = "sportivelocations",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SportiveLocation>> getAllSportiveLocations() {
        List<SportiveLocation> sportiveLocations = (List<SportiveLocation>) locationRepo.findAll();
        return new ResponseEntity<>(sportiveLocations, HttpStatus.OK);
    }


    /**
     *
     *      url  :   http://localhost:8080/proiectcolectiv/sportivelocations/{id}
     *
     * @param id - id of requested SportiveLocation
     * @return - the SportiveLocation with given id and OK status, if exists
     *           error message and NOT_FOUND status (404), if don't exist
     */
    @GetMapping(path = "sportivelocations/{id}")
    public ResponseEntity<?> getSportiveLocationById(@PathVariable Long id){
        SportiveLocation sl = null;
        for(SportiveLocation i : locationRepo.findAll()){
            if(i.getID().equals(id))
                sl = i;
        }
        if(sl != null){
            return new ResponseEntity<>(sl,HttpStatus.OK);
        }
        return new ResponseEntity<>("Sportive location don't exist!",HttpStatus.BAD_REQUEST);
    }


    /**
     *          url :  http://localhost:8080/proiectcolectiv/reservations
     *
     * @return
     */
    @GetMapping(path = "reservations", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllReservations(){

            // code to use after proper data retrieve from db
        Iterable<Reservation> reservations = reservationRepo.findAll();
        List<Reservation> responseList = new ArrayList<>();
        if(reservations != null){
           for(var res: reservations){
               responseList.add(res);
           }
           return new ResponseEntity<List<Reservation>>(responseList,HttpStatus.OK);
        }
        return new ResponseEntity<String>("Reservations can't be found!", HttpStatus.INTERNAL_SERVER_ERROR);


        //using mock data
//        List<Reservation> mockList = new ArrayList<>();
//
//        User u1 = new User("cineva", "parola_mea");
//        u1.setID(1l);
//        User u2 = new User("altcineva", "parola_mea2");
//        u1.setID(2l);
//
//        SportiveLocation s1 = new SportiveLocation(1l, "Baza sportiva Gheorgheni", "Undeva langa Iulius Mall, nr. 33", "Loc cu de toate pentru recreatie.", 100d, LocalTime.of(06, 00), LocalTime.of(22, 00), new HashSet<Day>(Arrays.asList(Day.values())){});
//
//
//        mockList.add(new Reservation(1l,LocalDateTime.of(2021,11,20,14,00,00),LocalDateTime.of(2021,11,20,16,00,00),u1,s1));
//        mockList.add(new Reservation(2l,LocalDateTime.of(2021,11,21,16,00,00),LocalDateTime.of(2021,11,21,18,00,00),u2,s1));
//        mockList.get(0).setCurrentNumberOfPlayers(7);
//        mockList.get(1).setCurrentNumberOfPlayers(5);
//        mockList.get(0).setMaxNumberOfPlayers(11);
//        mockList.get(1).setMaxNumberOfPlayers(11);
//
//        return new ResponseEntity<>(mockList,HttpStatus.OK);
    }

    //test method
    @GetMapping(path = "hello",produces = MediaType.APPLICATION_JSON_VALUE)
    public String hello(){
        //return new ResponseEntity<>("Hello",HttpStatus.OK);
        return "Hello";
    }

}
