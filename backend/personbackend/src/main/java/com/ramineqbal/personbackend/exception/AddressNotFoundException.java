package com.ramineqbal.personbackend.exception;

/**
 * AddressNotFoundException is a subclass of RuntimeException and should be used if an Address within com.ramineqbal.personbackend is not found.
 */
public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException(String string) {
        super(string);
    }

}

