package com.travel.travel.exception;

import java.nio.file.AccessDeniedException;
import javax.naming.AuthenticationException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
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

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<BodyErrorMessage> manejarErrorDeConexion(DataAccessException ex) {
        BodyErrorMessage response = new BodyErrorMessage();
        response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage("Error de conexión con la base de datos: " + ex.getMessage()); 
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BodyErrorMessage> handleGeneralException(Exception ex){
        BodyErrorMessage message = new BodyErrorMessage();
        message.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        message.setMessage("Error inesperado " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
        // Aquí puedes registrar el error
        System.err.println("Authentication error: " + ex.getMessage());
        return new ResponseEntity<>("Authentication failed: " + ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        // Aquí puedes registrar el error
        System.err.println("Access denied: " + ex.getMessage());
        return new ResponseEntity<>("Access denied: " + ex.getMessage(), HttpStatus.FORBIDDEN);
    }

}

