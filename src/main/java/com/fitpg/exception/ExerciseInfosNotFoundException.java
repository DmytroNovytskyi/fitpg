package com.fitpg.exception;

import com.fitpg.exception.basic.NotFoundException;

import java.util.Arrays;
import java.util.Collection;

/**
 * Custom exception class for a scenario where exercise infos cannot be found.
 */
public class ExerciseInfosNotFoundException extends NotFoundException {

    /**
     * Custom message for ExerciseInfosNotFoundException.
     */
    private static final String MESSAGE = "ExerciseInfos %s were not found!";

    /**
     * Constructor for ExerciseInfosNotFoundException.
     */
    public ExerciseInfosNotFoundException(Collection<String> exerciseInfos) {
        super(MESSAGE.formatted(Arrays.toString(exerciseInfos.toArray())));
    }
}
