package com.api.parcelservice.exception;

public class UserInactiveException extends RuntimeException {

    public UserInactiveException() {
        super();
    }

    public UserInactiveException(String message) {
        super(message);
    }
}
