package com.ramineqbal.personbackend.exception;


/**
 *IllegalAddressException is a subclass of RuntimeException and should be used if an Address within com.ramineqbal.personbackend that is to be added to the Database does not contain all the required Data according to com.ramineqbal.personbackend.model.Address.isValid()
 */
public class IllegalAddressException extends RuntimeException {
    

    public IllegalAddressException(String message) {
        super(message);
    }
}
