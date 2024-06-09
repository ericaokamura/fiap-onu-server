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
public class AmizadeDTO {

    private String seguidorNomeUsuario;
    private String seguidoNomeUsuario;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private boolean amigos;

}

