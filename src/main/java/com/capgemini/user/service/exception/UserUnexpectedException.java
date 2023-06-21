package com.capgemini.user.service.exception;

public class UserUnexpectedException extends RuntimeException {
    public UserUnexpectedException(Throwable cause) {
        super(cause);
    }
}
