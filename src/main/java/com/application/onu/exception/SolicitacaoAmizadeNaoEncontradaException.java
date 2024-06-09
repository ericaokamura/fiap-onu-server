package com.application.onu.exception;

public class SolicitacaoAmizadeNaoEncontradaException extends RuntimeException {

    public SolicitacaoAmizadeNaoEncontradaException() {
        super();
    }
    public SolicitacaoAmizadeNaoEncontradaException(String message) {
        super(message);
    }

    public SolicitacaoAmizadeNaoEncontradaException(String message, Throwable cause) {
        super(message, cause);
    }
}
