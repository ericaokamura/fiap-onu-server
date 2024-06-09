package com.application.onu.mapper;

import com.application.onu.model.Amizade;
import com.application.onu.model.SolicitacaoAmizade;
import com.application.onu.model.dto.AmizadeDTO;
import com.application.onu.model.dto.SolicitacaoAmizadeDTO;

import java.util.ArrayList;
import java.util.List;

public class AmizadeMapper {


    public static AmizadeDTO convertModelToDto(Amizade model) {
        AmizadeDTO dto = new AmizadeDTO();
        dto.setAmigos(model.isAmigos());
        dto.setDataHoraFim(model.getDataHoraFim());
        dto.setSeguidorNomeUsuario(model.getSeguidor().getNomeUsuario());
        dto.setSeguidoNomeUsuario(model.getSeguido().getNomeUsuario());
        dto.setDataHoraInicio(model.getDataHoraInicio());
        dto.setDataHoraFim(model.getDataHoraFim());
        return dto;
    }

    public static List<AmizadeDTO> convertListModelToListDto(List<Amizade> models) {
        List<AmizadeDTO> amizades = new ArrayList<>();
        models.forEach(m -> {
            amizades.add(convertModelToDto(m));
        });
        return amizades;
    }
}
