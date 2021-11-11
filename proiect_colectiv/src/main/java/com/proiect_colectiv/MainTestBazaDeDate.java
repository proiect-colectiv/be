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
        /*
        ISportiveLocationRepo repo= new SportiveLocationRepo();
        repo.save(new SportiveLocation("Terene de forbal", "acasa", "e naspa", 69.69, LocalTime.now(),LocalTime.now(),
                new HashSet<Day>(Arrays.asList( Day.MONDAY,Day.FRIDAY)){
        }));

        for(SportiveLocation sl:repo.findAll()){
            System.out.println(sl);
        }*/
        IReservationRepo repo=new ReservationRepo();

        for(Reservation sl:repo.findAll()) {
            System.out.println(sl);
        }
    }
}
