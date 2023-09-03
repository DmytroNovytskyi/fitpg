package com.fitpg.exception;

import com.fitpg.exception.basic.NotFoundException;

/**
 * Custom exception class for a scenario where a user cannot be found.
 */
public class UserNotFoundException extends NotFoundException {

    /**
     * Custom message for UserNotFoundException.
     */
    private static final String MESSAGE = "User was not found!";

    /**
     * Constructor for UserNotFoundException.
     */
    public UserNotFoundException() {
        super(MESSAGE);
    }
}
