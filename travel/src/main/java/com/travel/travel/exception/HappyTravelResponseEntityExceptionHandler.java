package com.travel.travel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.travel.travel.exception.dto.BodyErrorMessage;

@ControllerAdvice
public class HappyTravelResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(HappyTravelException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<BodyErrorMessage> handleHappyTravelException(HappyTravelException exception) {
        
        BodyErrorMessage bodyErrorMessage = new BodyErrorMessage();
        bodyErrorMessage.setHttpStatus(HttpStatus.NOT_ACCEPTABLE.value());
        bodyErrorMessage.setMessage(exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(bodyErrorMessage);

    }

}
