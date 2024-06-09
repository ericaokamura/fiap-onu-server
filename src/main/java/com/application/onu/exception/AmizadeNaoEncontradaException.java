package com.application.onu.exception;

public class AmizadeNaoEncontradaException extends RuntimeException {

    public AmizadeNaoEncontradaException() {
        super();
    }
    public AmizadeNaoEncontradaException(String message) {
        super(message);
    }

    public AmizadeNaoEncontradaException(String message, Throwable cause) {
        super(message, cause);
    }
}
