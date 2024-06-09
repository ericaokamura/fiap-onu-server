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
public class Amizade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Usuario seguidor;
    @OneToOne
    private Usuario seguido;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private boolean amigos;

}

