package com.fitpg.exception;

import com.fitpg.exception.basic.NotFoundException;

/**
 * Custom exception class for a scenario where a muscle group cannot be found.
 */
public class MuscleGroupNotFoundException extends NotFoundException {

    /**
     * Custom message for MuscleGroupNotFoundException.
     */
    private static final String MESSAGE = "MuscleGroup was not found!";

    /**
     * Constructor for MuscleGroupNotFoundException.
     */
    public MuscleGroupNotFoundException() {
        super(MESSAGE);
    }
}
