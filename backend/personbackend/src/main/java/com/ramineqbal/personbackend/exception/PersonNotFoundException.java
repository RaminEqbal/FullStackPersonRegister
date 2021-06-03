package com.ramineqbal.personbackend.exception;

public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(String string) {
        super(string);
    }

}
