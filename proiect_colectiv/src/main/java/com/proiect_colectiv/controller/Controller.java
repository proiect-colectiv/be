package com.proiect_colectiv.controller;


import com.proiect_colectiv.model.Reservation;
import com.proiect_colectiv.model.SportiveLocation;
import com.proiect_colectiv.model.User;
import com.proiect_colectiv.registration.RegistrationRequest;
import com.proiect_colectiv.repository.RepositoryInterfaces.IReservationRepo;
import com.proiect_colectiv.repository.RepositoryInterfaces.ISportiveLocationRepo;
import com.proiect_colectiv.service.IUserService;
import com.proiect_colectiv.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@CrossOrigin
@RestController()
public class Controller {
    private static final String INVALID_CREDENTIALS_ERROR_MESSAGE = "Invalid username or password!";
    private static final String USERNAME_ALREADY_EXISTS_ERROR_MESSAGE = "Username already exists!";
    private static final String SUCCESS_MESSAGE = "Succes!";
    private static final String INVALID_ID_ERROR_MESSAGE = "Invalid id!";


//    @Autowired
//    IReservationService reservationService;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private IReservationRepo reservationRepo;

    @Autowired
    private ISportiveLocationRepo locationRepo;

    @Autowired
    private Validator validator;

    @Autowired
    private IUserService userService;


    /**
     * url  :   http://localhost:8080/proiectcolectiv/sportivelocations
     *
     * @return the list containing all SportiveLocations that exists, in JSON format
     */

    @GetMapping(path = "sportivelocations", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SportiveLocation>> getAllSportiveLocations() {
        List<SportiveLocation> sportiveLocations = (List<SportiveLocation>) locationRepo.findAll();
        return new ResponseEntity<>(sportiveLocations, HttpStatus.OK);
    }


    /**
     * url  :   http://localhost:8080/proiectcolectiv/sportivelocations/{id}
     *
     * @param id - id of requested SportiveLocation
     * @return - the SportiveLocation with given id and OK status, if exists
     * error message and NOT_FOUND status (404), if don't exist
     */
    @GetMapping(path = "sportivelocations/{id}")
    public ResponseEntity<?> getSportiveLocationById(@PathVariable Long id) {
        SportiveLocation sl = null;
        for (SportiveLocation i : locationRepo.findAll()) {
            if (i.getID().equals(id))
                sl = i;
        }
        if (sl != null) {
            return new ResponseEntity<>(sl, HttpStatus.OK);
        }
        return new ResponseEntity<>("Sportive location don't exist!", HttpStatus.BAD_REQUEST);
    }


    /**
     * url :  http://localhost:8080/proiectcolectiv/reservations
     *
     * @return
     */
    @GetMapping(path = "reservations", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllReservations() {

        // code to use after proper data retrieve from db
        Iterable<Reservation> reservations = reservationRepo.findAll();
        List<Reservation> responseList = new ArrayList<>();
        if (reservations != null) {
            for (var res : reservations) {
                responseList.add(res);
            }
            return new ResponseEntity<List<Reservation>>(responseList, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Reservations can't be found!", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    /**
     * url:    http://localhost:8080/proiectcolectiv/registration
     * <p>
     * Create a new User with provided parameters
     *
     * @param request - the request object that needs to contain following fields
     *                username, firstName, lastName, password ,  as JSON object
     * @return -
     */
    @PostMapping(path = "registration", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request) {
        boolean passwordIsValid = validator.validatePassword(request.getPassword());
        boolean usernameIsValid = validator.validateUsername(request.getUsername());
        if (!(passwordIsValid && usernameIsValid)) {
            return new ResponseEntity<>(INVALID_CREDENTIALS_ERROR_MESSAGE, HttpStatus.BAD_REQUEST);
        }
        //see if an user with the same username already exists
        if (userService.findUserByUsername(request.getUsername()) != null) {
            return new ResponseEntity<>(USERNAME_ALREADY_EXISTS_ERROR_MESSAGE, HttpStatus.BAD_REQUEST);
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        //TODO: use firstName and lastName when the fields will be added to User entity
        User user = new User(
                request.getUsername(),
                encodedPassword
        );

        userService.save(user);
        return new ResponseEntity<>(SUCCESS_MESSAGE, HttpStatus.OK);

    }


    /**
     * url:   http://localhost:8080/proiectcolectiv/users/{id}
     * <p>
     * Provide access to user data through user id
     *
     * @param id - user id
     * @return - the user which has the specified id , if exist
     * - an error message and 404 NOT_FOUND error message otherwise
     */
    @GetMapping(path = "users/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        User user = userService.findOne(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(INVALID_ID_ERROR_MESSAGE, HttpStatus.NOT_FOUND);
    }


    /**
     * url:   http://localhost:8080/proiectcolectiv/reservations/{id}
     * <p>
     * Provide access to reservation details through reservation id
     *
     * @param id - reservation id
     * @return - the reservation with provided id, if exists
     * - error message and 404 NOT_FOUND status , otherwise
     */
    @GetMapping(path = "reservations/{id}")
    public ResponseEntity<?> getReservation(@PathVariable Long id) {
        Reservation reservation = reservationRepo.findOne(id);
        if (reservation != null) {
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        }
        return new ResponseEntity<>(INVALID_ID_ERROR_MESSAGE, HttpStatus.NOT_FOUND);
    }


    //test method
    @GetMapping(path = "hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public String hello() {
        //return new ResponseEntity<>("Hello",HttpStatus.OK);
        return "Hello";
    }

}
