package com.jedzer.dao.exception;

public class LessonNotFoundException extends Exception {
    public LessonNotFoundException() {
        super();
    }

    public LessonNotFoundException(String message) {
        super(message);
    }

    public LessonNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public LessonNotFoundException(Throwable cause) {
        super(cause);
    }

    protected LessonNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
