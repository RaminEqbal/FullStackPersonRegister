package com.ramineqbal.personbackend.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String string) {
        super(string);
    }

}
