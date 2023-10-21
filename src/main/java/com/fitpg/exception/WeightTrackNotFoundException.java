package com.fitpg.exception;

import com.fitpg.exception.basic.NotFoundException;

/**
 * Custom exception class for a scenario where a weight track cannot be found.
 */
public class WeightTrackNotFoundException extends NotFoundException {

    /**
     * Custom message for WeightTrackNotFoundException.
     */
    private static final String MESSAGE = "Weight track was not found!";

    /**
     * Constructor for WeightTrackNotFoundException.
     */
    public WeightTrackNotFoundException() {
        super(MESSAGE);
    }
}
