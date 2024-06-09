package com.application.onu.mapper;

import com.application.onu.model.Doacao;
import com.application.onu.model.SolicitacaoAmizade;
import com.application.onu.model.dto.DoacaoDTO;
import com.application.onu.model.dto.SolicitacaoAmizadeDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SolicitacaoAmizadeMapper {


    public static SolicitacaoAmizadeDTO convertModelToDto(SolicitacaoAmizade model) {
        SolicitacaoAmizadeDTO dto = new SolicitacaoAmizadeDTO();
        dto.setSolicitacaoId(model.getId());
        dto.setDataHoraSolicitacao(model.getDataHoraSolicitacao());
        dto.setAmizadeAprovada(model.isAmizadeAprovada());
        dto.setRecebeNomeUsuario(model.getRecebe().getNomeUsuario());
        dto.setSolicitanteNomeUsuario(model.getSolicitante().getNomeUsuario());
        dto.setDataHoraDelecao(model.getDataHoraDelecao());
        dto.setDataHoraAprovacao(model.getDataHoraAprovacao());
        return dto;
    }

    public static List<SolicitacaoAmizadeDTO> convertListModelToListDto(List<SolicitacaoAmizade> models) {
        List<SolicitacaoAmizadeDTO> solicitacoes = new ArrayList<>();
        models.forEach(m -> {
            solicitacoes.add(convertModelToDto(m));
        });
        return solicitacoes;
    }
}
