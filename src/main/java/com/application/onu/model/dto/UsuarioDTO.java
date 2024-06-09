package com.application.onu.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private String nomeUsuario;
    private String email;
    private String senha;
    private String nomeCompleto;
    private byte[] fotoPerfil;
    private LocalDateTime ultimaAtualizacao;

}
