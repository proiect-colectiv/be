package com.proiect_colectiv;

import com.proiect_colectiv.model.Day;
import com.proiect_colectiv.model.Reservation;
import com.proiect_colectiv.model.SportiveLocation;
import com.proiect_colectiv.model.User;
import com.proiect_colectiv.repository.RepositoryImplementations.ReservationRepo;
import com.proiect_colectiv.repository.RepositoryImplementations.SportiveLocationRepo;
import com.proiect_colectiv.repository.RepositoryImplementations.UserRepo;
import com.proiect_colectiv.repository.RepositoryInterfaces.IReservationRepo;
import com.proiect_colectiv.repository.RepositoryInterfaces.ISportiveLocationRepo;
import com.proiect_colectiv.repository.RepositoryInterfaces.IUserRepo;
import com.proiect_colectiv.service.IReservationService;
import com.proiect_colectiv.service.IUserService;
import com.proiect_colectiv.service.ReservationService;
import com.proiect_colectiv.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;

public class MainTestBazaDeDate {

    //Testam un pic baza de date, pup
    public static void main(String[] args) {

        //ASTA MERGE
        /*
        IUserRepo userRepo=new UserRepo();
        userRepo.save(new User("Ovidiu","sasesasepoartaincasa"));
        userRepo.save(new User("Radu","patrudedoi"));
        User de_sters=new User("Ronaldo","siuuuuuuuuuu");
        userRepo.save(de_sters);
        for(User user: userRepo.findAll()){
            System.out.println(user);
        }
        userRepo.delete(de_sters.getID());
        for(User user: userRepo.findAll()){
            System.out.println(user);
        }*/

        //SI ASTA MERGE

        /*ISportiveLocationRepo repo= new SportiveLocationRepo();
        repo.save(new SportiveLocation("Terene de forbal", "acasa", "e naspa", 69.69, LocalTime.now(),LocalTime.now(),
                new HashSet<Day>(Arrays.asList( Day.MONDAY,Day.FRIDAY)){
        }));
        repo.save(new SportiveLocation("Baza Sportiva Gheorgheni","Strada Alexandru Vaida Voevod, Cluj-Napoca","",70,LocalTime.of(10,00),LocalTime.of(20,00),  new HashSet<Day>(Arrays.asList( Day.MONDAY,Day.FRIDAY)){));

        for(SportiveLocation sl:repo.findAll()){
            System.out.println(sl);
        }*/
        IReservationRepo repo=new ReservationRepo();
        IReservationService service = new ReservationService(repo);

        System.out.println("AICI!!!!!!!!!!!!");
        for(Reservation sl:service.findAll()) {
            System.out.println("AICI!!!!!!!!!!!! IN FOR");
            System.out.println(sl);
        }

        IUserRepo userRepo = new UserRepo();
        User user = userRepo.findOne(1L);
        ISportiveLocationRepo sportiveLocationRepo = new SportiveLocationRepo();
        SportiveLocation sportiveLocation = sportiveLocationRepo.findOne(1L);
        sportiveLocation.setDescription("Descriere noua");
        System.out.println(sportiveLocation);
        sportiveLocation.setCloseTime(LocalTime.of(10,0,0,0));
        sportiveLocation.setOpenTime(LocalTime.of(11,0,0,0));
        sportiveLocation.setRentPrice(100.0);
        sportiveLocationRepo.update(sportiveLocation);
        Reservation reservation = repo.findOne(1L);
        reservation.setCurrentNumberOfPlayers(3);

        repo.update(reservation);

        repo.filterReservationByLocation(1L);
        repo.getAllReservationsAfterDate(LocalDateTime.of(2021,11,22,1,1));
        repo.filterReservationByDay(LocalDate.of(2021,11,21));

        IUserService userService = new UserService(userRepo);
        System.out.println(userService.validateUser(new User("xxxxxx123_","yy")));

    }
}
