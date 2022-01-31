package com.api.parcelservice.exception;

public class RegisterException extends RuntimeException {

    public RegisterException() {
        super();
    }

    public RegisterException(String message) {
        super(message);
    }
}
