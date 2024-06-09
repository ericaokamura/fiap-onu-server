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
public class ProdutoDTO {

    private Long anuncioId;
    private Long carrinhoId;
    private String titulo;
    private String descricao;
    private Double preco;
    private byte[] fotoAnuncio;
    private LocalDateTime dataHoraProdutoAdicionado;

}
