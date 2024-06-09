package com.application.onu.model.dto;

import com.application.onu.model.Anuncio;
import com.application.onu.model.Usuario;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoacaoDTO {

    private String titulo;
    private String descricao;
    private String nomeUsuario;
    private Long anuncioId;
    private LocalDateTime dataHoraDoacao;
}
