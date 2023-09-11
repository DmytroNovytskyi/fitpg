package com.fitpg.exception;

import com.fitpg.exception.basic.NotFoundException;

/**
 * Custom exception class for a scenario where an exercise info cannot be found.
 */
public class ExerciseInfoNotFoundException extends NotFoundException {

    /**
     * Custom message for ExerciseInfoNotFoundException.
     */
    private static final String MESSAGE = "ExerciseInfo was not found!";

    /**
     * Constructor for ExerciseInfoNotFoundException.
     */
    public ExerciseInfoNotFoundException() {
        super(MESSAGE);
    }
}
