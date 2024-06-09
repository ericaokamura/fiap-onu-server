package com.application.onu.mapper;

import com.application.onu.model.Configuracao;
import com.application.onu.model.dto.ConfiguracaoDTO;
import java.util.ArrayList;
import java.util.List;

public class ConfiguracaoMapper {

    public static ConfiguracaoDTO convertModelToDto(Configuracao model) {
        ConfiguracaoDTO dto = new ConfiguracaoDTO();
        dto.setAcessibilidade(model.isAcessibilidade());
        dto.setPerfilPublico(model.isPerfilPublico());
        dto.setUltimaAtualizacao(model.getUltimaAtualizacao());
        dto.setNomeUsuario(model.getUsuario().getNomeUsuario());
        return dto;
    }

    public static Configuracao convertDtoToModel(ConfiguracaoDTO dto) {
        Configuracao configuracao = new Configuracao();
        configuracao.setUltimaAtualizacao(dto.getUltimaAtualizacao());
        configuracao.setAcessibilidade(dto.isAcessibilidade());
        configuracao.setPerfilPublico(dto.isPerfilPublico());
        return configuracao;
    }

    public static List<ConfiguracaoDTO> convertListModelToListDto(List<Configuracao> models) {
        List<ConfiguracaoDTO> configuracoes = new ArrayList<>();
        models.forEach(m -> {
            configuracoes.add(convertModelToDto(m));
        });
        return configuracoes;
    }

    public static List<Configuracao> convertListDtoToListModel(List<ConfiguracaoDTO> dtos) {
        List<Configuracao> configuracoes = new ArrayList<>();
        dtos.forEach(d -> {
            configuracoes.add(convertDtoToModel(d));
        });
        return configuracoes;
    }

}


