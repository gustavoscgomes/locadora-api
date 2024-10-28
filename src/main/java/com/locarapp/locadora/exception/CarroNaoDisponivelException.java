package com.locarapp.locadora.exception;

public class CarroNaoDisponivelException extends RuntimeException{
    public CarroNaoDisponivelException(String message) {
        super(message);
    }
}
