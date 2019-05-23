package com.jedzer.dao.exception;

public class CourseAlreadyCreatedException extends Exception {
    public CourseAlreadyCreatedException() {
        super();
    }

    public CourseAlreadyCreatedException(String message) {
        super(message);
    }

    public CourseAlreadyCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CourseAlreadyCreatedException(Throwable cause) {
        super(cause);
    }

    protected CourseAlreadyCreatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
