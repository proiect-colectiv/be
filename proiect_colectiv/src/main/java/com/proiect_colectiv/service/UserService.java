package com.proiect_colectiv.service;

import com.proiect_colectiv.model.User;
import com.proiect_colectiv.repository.RepositoryInterfaces.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService implements IUserService{

    @Autowired
    public IUserRepo userRepo;

    public UserService(IUserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User findOne(Long id) {
        return userRepo.findOne(id);
    }

    @Override
    public Iterable<User> findAll() {
        return null;
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
        String regexUsername = "^[A-Za-z0-9_]\\w{5,29}$";
        Pattern pUsername = Pattern.compile(regexUsername);
        if (user.getUsername() == null) {
            return false;
        }
        Matcher mUsername = pUsername.matcher(user.getUsername());

        String regexEmail = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pEmail = Pattern.compile(regexEmail);
        if (user.getEmail() == null)
            return false;
        Matcher mEmail = pEmail.matcher(user.getEmail());


        return mUsername.matches() && mEmail.matches();
    }
}
