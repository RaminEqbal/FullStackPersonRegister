package com.ramineqbal.personbackend.exception;


/**
 * PersonNotFoundException is a subclass of RuntimeException and should be used if a Person within com.ramineqbal.personbackend is not found.
 */
public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(String string) {
        super(string);
    }

}
