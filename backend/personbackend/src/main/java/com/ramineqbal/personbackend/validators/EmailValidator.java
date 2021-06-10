package com.ramineqbal.personbackend.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * Class containing available E-Mail Validators
 * All Validator Methods are static and Patterns are exposed
 */
public class EmailValidator {
    

    /**
     * Simple Regex Pattern for E-Mail addresses.
     * May not be exhaustive
     */
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);



    /**
     * Validates an input E-Mail based on the Regex Pattern EmailValidator.VALID_EMAIL_ADDRESS_REGEX
     * @param email The E-Mail that is to be validated
     * @return returns true, if the E-Mail is valid according to VALID_EMAIL_ADDRESS_REGEX, if else then returns false
     */
    public static boolean isValidEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }


}
