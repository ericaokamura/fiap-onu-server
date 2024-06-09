package com.application.onu.exception;

import com.application.onu.model.Perfil;
import com.application.onu.model.SolicitacaoAmizade;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity usuarioNaoEncontrado() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AmizadeNaoEncontradaException.class)
    public ResponseEntity amizadeNaoEncontradaException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AnuncioNaoEncontradoException.class)
    public ResponseEntity anuncioNaoEncontradoException(AnuncioNaoEncontradoException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(CarrinhoNaoEncontradoException.class)
    public ResponseEntity carrinhoNaoEncontradoException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(PerfilNaoEncontradoException.class)
    public ResponseEntity perfilNaoEncontradoException(PerfilNaoEncontradoException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(SolicitacaoAmizadeNaoEncontradaException.class)
    public ResponseEntity solicitacaoAmizadeNaoEncontradaException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(UsuarioJaExistenteException.class)
    public ResponseEntity usuarioJaExistenteException() {
        return ResponseEntity.badRequest().build();
    }

}
