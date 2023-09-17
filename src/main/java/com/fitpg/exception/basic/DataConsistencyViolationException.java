package com.fitpg.exception.basic;

import com.fitpg.exception.wrapper.ExceptionType;

public abstract class DataConsistencyViolationException extends ServiceException {

    /**
     * Constructor for DataConsistencyViolationException.
     *
     * @param message the exception message with problem description
     */
    public DataConsistencyViolationException(String message) {
        super(message, ExceptionType.DATABASE_EXCEPTION);
    }
}
