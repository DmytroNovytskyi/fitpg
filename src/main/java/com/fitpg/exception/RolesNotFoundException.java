package com.fitpg.exception;

import com.fitpg.exception.basic.NotFoundException;

import java.util.Arrays;
import java.util.Collection;

/**
 * Custom exception class for a scenario where roles cannot be found.
 */
public class RolesNotFoundException extends NotFoundException {

    /**
     * Custom message for RoleNotFoundException.
     */
    private static final String MESSAGE = "Roles %s were not found!";

    /**
     * Constructor for RoleNotFoundException.
     */
    public RolesNotFoundException(Collection<String> roles) {
        super(MESSAGE.formatted(Arrays.toString(roles.toArray())));
    }
}
