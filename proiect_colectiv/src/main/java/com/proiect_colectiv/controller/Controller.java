package com.proiect_colectiv.controller;


import com.proiect_colectiv.model.Days;
import com.proiect_colectiv.model.SportiveLocation;
import com.proiect_colectiv.model.User;
import org.apache.tomcat.jni.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.*;

@CrossOrigin
@RestController()
public class Controller {

    //placeholder list until the flow of data from DB -> repo-> service is ready to use
    static List<SportiveLocation> replacementList = new ArrayList<>();
    static Set<Days> allDays = new HashSet<>(Arrays.asList(Days.values()));
    static{
        replacementList.add(new SportiveLocation(1l,"Baza Sportiva Gheorgheni","Strada Alexandru Vaida Voevod, Cluj-Napoca","Centru sportiv cu de toate pentru toti.",120d, LocalTime.of(6,0),LocalTime.of(23,00), allDays));
        replacementList.add(new SportiveLocation(2l,"Baza Test 2","Strada test 2","Descriere test 2",120d, LocalTime.of(6,0),LocalTime.of(22,00), allDays));
        replacementList.add(new SportiveLocation(3l,"Baza Test 3","Strada test 3","Descriere test 3",150d, LocalTime.of(7,0),LocalTime.of(22,00), allDays));
        replacementList.add(new SportiveLocation(4l,"Baza Test 4","Strada test 4","Descriere test 4",45d, LocalTime.of(8,0),LocalTime.of(22,00), allDays));
        replacementList.add(new SportiveLocation(5l,"Baza Test 5","Strada test 5","Descriere test 5",12d, LocalTime.of(10,0),LocalTime.of(22,00), allDays));
    }

    /**
     *
     *      url  :   http://localhost:8080/proiectcolectiv/sportivelocations
     *
     * @return the list containing all SportiveLocations that exists, in JSON format
     */

    @GetMapping(path = "sportivelocations",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SportiveLocation>> getAllSportiveLocations() {
        List<SportiveLocation> sportiveLocations = replacementList;
        System.out.println(replacementList);
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
        for(SportiveLocation i : replacementList){
            if(i.getID().equals(id))
                sl = i;
        }
        if(sl != null){
            return new ResponseEntity<>(sl,HttpStatus.OK);
        }
        return new ResponseEntity<>("Sportive location don't exist!",HttpStatus.NOT_FOUND);
    }


    //test method
    @GetMapping(path = "hello",produces = MediaType.APPLICATION_JSON_VALUE)
    public String hello(){
        //return new ResponseEntity<>("Hello",HttpStatus.OK);
        return "Hello";
    }

}
