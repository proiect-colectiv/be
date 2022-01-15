package com.proiect_colectiv.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.proiect_colectiv.model.FilterDTO;
import com.proiect_colectiv.model.Reservation;
import com.proiect_colectiv.model.SportiveLocation;
import com.proiect_colectiv.model.User;
import com.proiect_colectiv.model.authentication.AuthenticationRequest;
import com.proiect_colectiv.model.authentication.AuthenticationResponse;
import com.proiect_colectiv.model.registration.RegistrationRequest;
import com.proiect_colectiv.repository.RepositoryInterfaces.IReservationRepo;
import com.proiect_colectiv.repository.RepositoryInterfaces.ISportiveLocationRepo;
import com.proiect_colectiv.service.IReservationService;
import com.proiect_colectiv.service.IUserService;
import com.proiect_colectiv.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.proiect_colectiv.utils.JwtTokenUtil.createToken;


@CrossOrigin
@RestController()
public class Controller {
    private static final String INVALID_CREDENTIALS_ERROR_MESSAGE = "Invalid username or password!";
    private static final String USERNAME_ALREADY_EXISTS_ERROR_MESSAGE = "Username already exists!";
    private static final String SUCCESS_MESSAGE = "Succes!";
    private static final String INVALID_ID_ERROR_MESSAGE = "Invalid id!";
    private static final String INVALID_EMAIL_ERROR_MESSAGE = "Invalid email!";
    private static final String NO_SUCH_USERNAME_ERROR_MESSAGE = "Username not exists!";
    private static final String INVALID_PASSWORD_ERROR_MESSAGE = "Invalid password!";


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
    private IReservationService reservationService;

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
     * @return the list of existing reservations not already passed, sorted by startDate.
     */
    @GetMapping(path = "reservations", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllReservations() {
        List<Reservation> responseList = new ArrayList<>();
        reservationService.getFutureReservations().forEach((Reservation res) ->
                responseList.add(res)
        );

        return new ResponseEntity<List<Reservation>>(responseList, HttpStatus.OK);
    }

    /**
     * url :  http://localhost:8080/proiectcolectiv/reservations
     *
     * @return the list of existing reservations not already passed, sorted by startDate,
     * filtered by filter non-null fields
     */
    @PostMapping(path = "reservations", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllReservationsFiltered(@RequestBody(required = false) FilterDTO filter) {
        List<Reservation> responseList = new ArrayList<>();
        reservationService.getReservationsFiltered(filter).forEach((Reservation res) ->
                responseList.add(res)
        );

        return new ResponseEntity<List<Reservation>>(responseList, HttpStatus.OK);
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
        //if email field exists, it must match the email pattern
        if (request.getEmail() != null && !validator.validateEmail(request.getEmail())) {
            return new ResponseEntity<>(INVALID_EMAIL_ERROR_MESSAGE, HttpStatus.BAD_REQUEST);
        }
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = new User(
                request.getUsername(),
                encodedPassword,
                request.getFirstName(),
                request.getLastName(),
                request.getEmail()
        );

        userService.save(user);
        return new ResponseEntity<>(SUCCESS_MESSAGE, HttpStatus.OK);

    }


    @PostMapping(path = "authenticate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        User user = userService.findUserByUsername(request.getUsername());
        //no user with provided username or bad password
        if (user == null) {
            return new ResponseEntity<>(NO_SUCH_USERNAME_ERROR_MESSAGE, HttpStatus.BAD_REQUEST);
        }
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new ResponseEntity<>(INVALID_PASSWORD_ERROR_MESSAGE, HttpStatus.BAD_REQUEST);
        }

        try {
            //generating token
            String jwtToken = createToken(user);
            return new ResponseEntity<>(new AuthenticationResponse(jwtToken), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
            return new ResponseEntity<User>(user, HttpStatus.OK);
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


    /**
     * url:   http://localhost:8080/proiectcolectiv/reservations/{id}/users
     * <p>
     * Provide access to users that participate to a reservation
     *
     * @param reservationId - id of reservation
     * @return - a list containing all users that participate to the reservation specified by reservationId
     * - 400 BAD_REQUEST status if the reservationId don't exists
     */
    @GetMapping(path = "reservations/{reservationId}/users")
    public ResponseEntity<?> getUsersForReservation(@PathVariable Long reservationId) {
        List<User> users = (ArrayList<User>) userService.getUsersForReservationId(reservationId);
        if (users != null && users.size() > 0) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(INVALID_ID_ERROR_MESSAGE, HttpStatus.BAD_REQUEST);
    }

    //test method
    @GetMapping(path = "hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public String hello() {
        //return new ResponseEntity<>("Hello",HttpStatus.OK);
        return "Hello";
    }

}
