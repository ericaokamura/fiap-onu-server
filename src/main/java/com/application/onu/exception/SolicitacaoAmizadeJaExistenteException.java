package com.application.onu.exception;

public class SolicitacaoAmizadeJaExistenteException extends RuntimeException {


    public SolicitacaoAmizadeJaExistenteException() {
        super();
    }
    public SolicitacaoAmizadeJaExistenteException(String message) {
        super(message);
    }

    public SolicitacaoAmizadeJaExistenteException(String message, Throwable cause) {
        super(message, cause);
    }



}
