package com.application.onu.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PerfilDTO {
    private String nomeUsuario;
    private Long numeroAnuncios;
    private Long numeroSeguidores;
    private Long numeroSeguindo;
    private byte[] fotoPerfil;
    private LocalDate dataCadastro;
}
