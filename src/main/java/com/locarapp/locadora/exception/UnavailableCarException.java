package com.locarapp.locadora.exception;

public class UnavailableCarException extends RuntimeException{
    public UnavailableCarException(String message) {
        super(message);
    }
}
