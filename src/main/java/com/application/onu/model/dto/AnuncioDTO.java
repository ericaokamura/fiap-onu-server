package com.application.onu.model.dto;

import com.application.onu.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnuncioDTO {

    private Long id;
    private String nomeUsuario;
    private String titulo;
    private String descricao;
    private String tipoAnuncio;
    private boolean disponibilidade;
    private Double preco;
    private LocalDateTime dataHoraPublicacao;
    private byte[] fotoAnuncio;

}
