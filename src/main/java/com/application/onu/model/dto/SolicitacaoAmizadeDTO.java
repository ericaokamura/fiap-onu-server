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
public class SolicitacaoAmizadeDTO {

    private Long solicitacaoId;
    private String solicitanteNomeUsuario;
    private String recebeNomeUsuario;
    private boolean amizadeAprovada;
    private LocalDateTime dataHoraSolicitacao;
    private LocalDateTime dataHoraAprovacao;
    private LocalDateTime dataHoraDelecao;

}
