package com.fitpg.exception;

import com.fitpg.exception.basic.NotFoundException;

/**
 * Custom exception class for a scenario where an exercise cannot be found.
 */
public class ExerciseNotFoundException extends NotFoundException {

    /**
     * Custom message for ExerciseNotFoundException.
     */
    private static final String MESSAGE = "Exercise was not found!";

    /**
     * Constructor for ExerciseNotFoundException.
     */
    public ExerciseNotFoundException() {
        super(MESSAGE);
    }
}
