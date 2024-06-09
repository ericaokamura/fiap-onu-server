package com.application.onu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.io.File;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Anuncio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Usuario usuario;
    private String titulo;
    private String descricao;
    private String tipoAnuncio;
    private boolean disponibilidade;
    private Double preco;
    private LocalDateTime dataHoraPublicacao;
    private byte[] fotoAnuncio;

}
