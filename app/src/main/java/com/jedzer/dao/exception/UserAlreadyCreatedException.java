package com.jedzer.dao.exception;

public class UserAlreadyCreatedException extends Exception {
    public UserAlreadyCreatedException() {
        super();
    }

    public UserAlreadyCreatedException(String message) {
        super(message);
    }

    public UserAlreadyCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyCreatedException(Throwable cause) {
        super(cause);
    }

    protected UserAlreadyCreatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
