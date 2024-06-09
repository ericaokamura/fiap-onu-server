package com.application.onu.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolicitacaoAmizade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Usuario solicitante;
    @OneToOne
    private Usuario recebe;
    private boolean amizadeAprovada;
    private LocalDateTime dataHoraSolicitacao;
    private LocalDateTime dataHoraAprovacao;
    private LocalDateTime dataHoraDelecao;

}
