package com.proiect_colectiv.repository;

import com.proiect_colectiv.model.User;

public interface IUserRepo extends IRepo<User> {
    User findOneByUsername(String username);
}
