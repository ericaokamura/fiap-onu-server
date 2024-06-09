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
public class ConfiguracaoDTO {

    private String nomeUsuario;
    private boolean perfilPublico;
    private boolean acessibilidade;
    private LocalDateTime ultimaAtualizacao;
}
