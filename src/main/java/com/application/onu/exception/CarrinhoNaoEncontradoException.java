package com.application.onu.exception;

public class CarrinhoNaoEncontradoException extends RuntimeException {
    public CarrinhoNaoEncontradoException() {
        super();
    }


    public CarrinhoNaoEncontradoException(String message) {
        super(message);
    }

    public CarrinhoNaoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }
}
