package com.application.onu.mapper;

import com.application.onu.model.Amizade;
import com.application.onu.model.Recebimento;
import com.application.onu.model.dto.AmizadeDTO;
import com.application.onu.model.dto.RecebimentoDTO;

import java.util.ArrayList;
import java.util.List;

public class RecebimentoMapper {

    public static RecebimentoDTO convertModelToDto(Recebimento model) {
        RecebimentoDTO dto = new RecebimentoDTO();
        dto.setDescricao(model.getDescricao());
        dto.setTitulo(model.getTitulo());
        dto.setNomeUsuario(model.getUsuario().getNomeUsuario());
        dto.setDataHoraRecebimento(model.getDataHoraRecebimento());
        dto.setAnuncioId(model.getAnuncio().getId());
        return dto;
    }

    public static List<RecebimentoDTO> convertListModelToListDto(List<Recebimento> models) {
        List<RecebimentoDTO> recebimentos = new ArrayList<>();
        models.forEach(m -> {
            recebimentos.add(convertModelToDto(m));
        });
        return recebimentos;
    }

    public static Recebimento convertDtoToModel(RecebimentoDTO dto) {
        Recebimento model = new Recebimento();
        model.setTitulo(dto.getDescricao());
        model.setDescricao(dto.getDescricao());
        model.setDataHoraRecebimento(dto.getDataHoraRecebimento());
        return model;
    }

    public static List<Recebimento> convertListDtoToListModel(List<RecebimentoDTO> dtos) {
        List<Recebimento> recebimentos = new ArrayList<>();
        dtos.forEach(d -> {
            recebimentos.add(convertDtoToModel(d));
        });
        return recebimentos;
    }
}
