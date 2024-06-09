package com.application.onu.model.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompraDTO {

    private String titulo;
    private String descricao;
    @OneToOne
    private String nomeUsuario;
    @OneToOne
    private Long anuncioId;
    private Double preco;
    private LocalDateTime dataHoraCompra;
}
