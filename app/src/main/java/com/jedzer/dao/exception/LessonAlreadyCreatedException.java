package com.jedzer.dao.exception;

public class LessonAlreadyCreatedException extends Exception {
    public LessonAlreadyCreatedException() {
        super();
    }

    public LessonAlreadyCreatedException(String message) {
        super(message);
    }

    public LessonAlreadyCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LessonAlreadyCreatedException(Throwable cause) {
        super(cause);
    }

    protected LessonAlreadyCreatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
