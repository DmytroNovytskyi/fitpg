package com.fitpg.exception;

import com.fitpg.exception.basic.NotFoundException;

/**
 * Custom exception class for a scenario where a nutrients track cannot be found.
 */
public class NutrientsTrackNotFoundException extends NotFoundException {

    /**
     * Custom message for NutrientsTrackNotFoundException.
     */
    private static final String MESSAGE = "Nutrients track was not found!";

    /**
     * Constructor for NutrientsTrackNotFoundException.
     */
    public NutrientsTrackNotFoundException() {
        super(MESSAGE);
    }
}
