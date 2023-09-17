package com.fitpg.exception;

import com.fitpg.exception.basic.NotFoundException;

/**
 * Custom exception class for a scenario where a workout cannot be found.
 */
public class WorkoutNotFoundException extends NotFoundException {

    /**
     * Custom message for WorkoutNotFoundException.
     */
    private static final String MESSAGE = "Workout was not found!";

    /**
     * Constructor for WorkoutNotFoundException.
     */
    public WorkoutNotFoundException() {
        super(MESSAGE);
    }
}
