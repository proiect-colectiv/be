package com.proiect_colectiv.utils;

import org.springframework.stereotype.Component;

import static com.proiect_colectiv.utils.Constants.PASSWORD_PATTERN;


@Component
public class Validator {

    /**
     * Validate a password
     *
     * @return true if password is valid
     * false , otherwise
     */
    public boolean validatePassword(String password) {
        return password.matches(PASSWORD_PATTERN.toString());
    }


    //TODO: logic for username validation
    public boolean validateUsername(String username) {
        return true;
    }

    ;

}
