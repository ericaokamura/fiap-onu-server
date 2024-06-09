package com.application.onu.exception;

public class PerfilNaoEncontradoException extends RuntimeException {

    public PerfilNaoEncontradoException() {
        super();
    }

    public PerfilNaoEncontradoException(String message) {
        super(message);
    }

    public PerfilNaoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }
}
