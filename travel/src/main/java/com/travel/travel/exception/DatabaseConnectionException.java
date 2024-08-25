package com.travel.travel.exception;

public class DatabaseConnectionException extends RuntimeException{
    public DatabaseConnectionException(String message){
        super(message);
    }
}
