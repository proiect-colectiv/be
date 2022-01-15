package com.proiect_colectiv;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.proiect_colectiv.model.User;

import static com.proiect_colectiv.utils.JwtTokenUtil.*;

public class MainTestBazaDeDate {

    //Testam un pic baza de date, pup
    public static void main(String[] args) {


        User u = new User("cineva", "parolaaa", "Nicu", "Aron", "cineva@undeva.com");
        u.setID(1l);

    //        try {
    //            String payload = createPayload(u);
    //            System.out.println(payload);
    //
    //            User u2 = getUserFromPayload(payload);
    //            System.out.println(u2);
    //        } catch (JsonProcessingException e) {
    //            throw new RuntimeException("Error creating payload!!");
    //        }

        try {
            String token = createToken(u);
            System.out.println(token);

            if(isValid(token)){
                System.out.println("Mergeeeee");
                User user = (User) getTokenPayloadAsObject(token);
                System.out.println(user);
            }
        } catch (JsonProcessingException e) {
            System.out.println("Error creating token");
        }

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
        }
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
        System.out.println(userService.validateUser(new User("xxxxxx123_","yy", "first", "last", "email@email.com")));
        */
        /*IUserRepo repo = new UserRepo();
        IUserService service = new UserService(repo);
        for(User u : repo.findAll()){
            System.out.println(u);
        }
        System.out.println(service.validateUser(new User("xxxxxx123_","yy", "first", "last", "email@email.com")));
        */

        //TEST GetFutureReservations()
        /*IReservationRepo repo=new ReservationRepo();
        IReservationService service = new ReservationService(repo);

        IUserRepo userRepo = new UserRepo();
        User user = userRepo.findOne(1L);

        ISportiveLocationRepo sportiveLocationRepo = new SportiveLocationRepo();
        SportiveLocation sportiveLocation = sportiveLocationRepo.findOne(1L);

        service.save(new Reservation(4L,LocalDateTime.of(2022,1,14,19,0),
                LocalDateTime.of(2022,1,14,19,50),user,sportiveLocation));
        service.save(new Reservation(5L,LocalDateTime.of(2022,1,14,18,30),
                LocalDateTime.of(2022,1,14,19,45),user,sportiveLocation));
        service.save(new Reservation(6L,LocalDateTime.of(2022,1,14,20,0),
                LocalDateTime.of(2022,1,14,21,0),user,sportiveLocation));
        service.save(new Reservation(7L,LocalDateTime.of(2022,1,14,18,0),
                LocalDateTime.of(2022,1,14,20,0),user,sportiveLocation));

        service.findAll();

        service.getFutureReservations();*/

//        //TEST getUsersForReservationId(Long reservationId)
//        IUserRepo userRepo = new UserRepo();
//        IReservationUserRepo reservationUserRepo = new ReservationUserRepo();
//        IUserService userService = new UserService(userRepo,reservationUserRepo);
//
//        userService.getUsersForReservationId(1L);

    }
}
