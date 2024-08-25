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
    public ResponseEntity<BodyErrorMessage> handleErrorDeConexion(DataAccessException ex) {
        BodyErrorMessage message = new BodyErrorMessage();
        message.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        message.setMessage("Error de conexión con la base de datos: " + ex.getMessage()); //Por eso el mensaje se pone aquí
        
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DuplicatedDestinationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<BodyErrorMessage> handleDuplicatedDestinationException(DuplicatedDestinationException ex){
        BodyErrorMessage message = new BodyErrorMessage();
        message.setHttpStatus(HttpStatus.CONFLICT.value());
        message.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @ExceptionHandler(Exception.class) // Error global
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<BodyErrorMessage> handleGeneralException(Exception ex){
        BodyErrorMessage message = new BodyErrorMessage();
        message.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        message.setMessage("Error inesperado " + ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }

    @ExceptionHandler(InvalidDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BodyErrorMessage> handleInvalidDataException(InvalidDataException ex){
        BodyErrorMessage message = new BodyErrorMessage();
        message.setHttpStatus(HttpStatus.BAD_REQUEST.value());
        message.setMessage(ex.getMessage());
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}

