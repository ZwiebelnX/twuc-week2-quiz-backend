package com.twuc.finalbackend.models.exception;

import org.springframework.http.HttpStatus;

public class CustomBasicException extends Exception {
    private final CustomCode errorCode;
    private final HttpStatus httpStatus;

    public CustomBasicException(HttpStatus httpStatus,CustomCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public CustomCode getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
