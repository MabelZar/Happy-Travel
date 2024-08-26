package com.travel.travel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.travel.travel.dto.BodyErrorMessage;

@ControllerAdvice
public class HappyTravelResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(HappyTravelException.class)
    public ResponseEntity<BodyErrorMessage> handleHappyTravelException(HappyTravelException exception) {

        HttpStatus httpStatus = exception.getHttpStatus();
        if (httpStatus == null) {
            httpStatus = HttpStatus.NOT_FOUND;
        }

        BodyErrorMessage bodyErrorMessage = new BodyErrorMessage();
        bodyErrorMessage.setHttpStatus(httpStatus.value());
        bodyErrorMessage.setMessage(exception.getMessage());

        return ResponseEntity.status(httpStatus).body(bodyErrorMessage);

    }

}
