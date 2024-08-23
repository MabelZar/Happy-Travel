package com.travel.travel.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.travel.travel.dto.BodyErrorMessage;

@ControllerAdvice
public class HappyTravelResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(HappyTravelException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<BodyErrorMessage> handleHappyTravelException(HappyTravelException exception) {
        
        BodyErrorMessage bodyErrorMessage = new BodyErrorMessage();
        bodyErrorMessage.setHttpStatus(HttpStatus.NOT_FOUND.value());
        bodyErrorMessage.setMessage(exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyErrorMessage);

    }

    @ExceptionHandler(DataAccessException.class) // Error global que solo se indica aquí
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<BodyErrorMessage> manejarErrorDeConexion(DataAccessException ex) {
        BodyErrorMessage response = new BodyErrorMessage();
        response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage("Error de conexión con la base de datos: " + ex.getMessage()); //Por eso el mensaje se pone aquí
        
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
