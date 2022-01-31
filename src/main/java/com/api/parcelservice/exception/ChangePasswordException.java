package com.api.parcelservice.exception;

public class ChangePasswordException extends RuntimeException {

    public ChangePasswordException() {
    }

    public ChangePasswordException(String message) {
        super(message);
    }
}
