package com.travel.travel.exception;

import org.springframework.http.HttpStatus;

public class HappyTravelException extends Exception {

    private HttpStatus httpStatus;

    public HappyTravelException(String message) {
        super(message);
    }
    
    public HappyTravelException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

}
