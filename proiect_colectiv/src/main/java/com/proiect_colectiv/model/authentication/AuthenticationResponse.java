package com.proiect_colectiv.model.authentication;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public AuthenticationResponse(String token) {
        this.token = token;
    }
}
