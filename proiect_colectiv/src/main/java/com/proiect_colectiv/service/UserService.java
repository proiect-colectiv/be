package com.proiect_colectiv.service;

import com.proiect_colectiv.model.User;
import com.proiect_colectiv.repository.RepositoryInterfaces.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
