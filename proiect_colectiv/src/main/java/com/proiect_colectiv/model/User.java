package com.proiect_colectiv.model;

public class User extends Entity{

    private String username;
    private String password;

    public User(Long ID,String username, String password) {
        super(ID);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
