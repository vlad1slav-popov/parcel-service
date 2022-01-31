package com.api.parcelservice.exception;

public class ParcelException extends RuntimeException {

    private String errorCode;

    public ParcelException() {
        super();
    }

    public ParcelException(String message,
                           String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
