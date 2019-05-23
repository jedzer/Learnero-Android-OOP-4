package com.jedzer.dao.exception;

public class QuizAlreadyCreatedException extends Exception {
    public QuizAlreadyCreatedException() {
        super();
    }

    public QuizAlreadyCreatedException(String message) {
        super(message);
    }

    public QuizAlreadyCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuizAlreadyCreatedException(Throwable cause) {
        super(cause);
    }

    protected QuizAlreadyCreatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
