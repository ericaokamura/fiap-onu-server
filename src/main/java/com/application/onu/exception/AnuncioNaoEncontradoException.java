package com.application.onu.exception;

public class AnuncioNaoEncontradoException extends RuntimeException {

    public AnuncioNaoEncontradoException() {
        super();
    }

    public AnuncioNaoEncontradoException(String message) {
        super(message);
    }

    public AnuncioNaoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }
}
