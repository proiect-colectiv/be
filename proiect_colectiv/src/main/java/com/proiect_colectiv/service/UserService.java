package com.proiect_colectiv.service;

import com.proiect_colectiv.model.User;
import com.proiect_colectiv.repository.RepositoryInterfaces.IReservationUserRepo;
import com.proiect_colectiv.repository.RepositoryInterfaces.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.proiect_colectiv.utils.Constants.EMAIL_PATTERN;
import static com.proiect_colectiv.utils.Constants.USERNAME_PATTERN;


@Service
public class UserService implements IUserService{

    @Autowired
    public IUserRepo userRepo;
    @Autowired
    public IReservationUserRepo reservationUserRepo;

    public UserService(IUserRepo userRepo, IReservationUserRepo reservationUserRepo) {
        this.userRepo = userRepo;
        this.reservationUserRepo = reservationUserRepo;
    }

    @Override
    public User findOne(Long id) {
        return userRepo.findOne(id);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public void save(User user) {
        userRepo.save(user);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public User findUserByUsername(String username) {
        return userRepo.findOneByUsername(username);
    }


    @Override
    public boolean validateUser(User user) {
        if (user.getUsername() == null || user.getEmail() == null) {
            return false;
        }

        Matcher mUsername = USERNAME_PATTERN.matcher(user.getUsername());
        Matcher mEmail = EMAIL_PATTERN.matcher(user.getEmail());

        return mUsername.matches() && mEmail.matches();
    }

    @Override
    public Iterable<User> getUsersForReservationId(Long reservationId) {
        List<User> users = new ArrayList<>();
        reservationUserRepo.filterByReservationID(reservationId).forEach(x->users.add(x.getUser()));
        return users;
    }
}
