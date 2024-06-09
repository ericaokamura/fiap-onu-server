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
public class RecebimentoDTO {
    private String titulo;
    private String descricao;
    private String nomeUsuario;
    private Long anuncioId;
    private LocalDateTime dataHoraRecebimento;
}

