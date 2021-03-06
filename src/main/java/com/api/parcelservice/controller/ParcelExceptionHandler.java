package com.api.parcelservice.controller;

import com.api.parcelservice.domain.ErrorResponse;
import com.api.parcelservice.exception.ParcelException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.annotation.Nonnull;

@RestControllerAdvice
public class ParcelExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ParcelException.class)
    public ResponseEntity<ErrorResponse> handleParcelException(ParcelException e) {
        return ResponseEntity.ok()
                .body(ErrorResponse.builder()
                        .description(e.getMessage())
                        .code(e.getErrorCode())
                        .build());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @Nonnull HttpHeaders headers,
                                                                  @Nonnull HttpStatus status,
                                                                  @Nonnull WebRequest request) {

        return ResponseEntity.ok()
                .body(
                        ErrorResponse.builder()
                                .code(String.valueOf(status.value()))
                                .description(ex.getBindingResult().getFieldError().getField() + " " +
                                        ex.getBindingResult().getFieldError().getDefaultMessage())
                                .build());

    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        return ResponseEntity.status(status)
                .body(ErrorResponse.builder()
                        .code(String.valueOf(status.value()))
                        .description(ex.getMessage())
                        .build())
                ;
    }
}
