package com.application.onu.mapper;

import com.application.onu.model.Compra;
import com.application.onu.model.Doacao;
import com.application.onu.model.dto.CompraDTO;
import com.application.onu.model.dto.DoacaoDTO;

import java.util.ArrayList;
import java.util.List;

public class DoacaoMapper {

    public static DoacaoDTO convertModelToDto(Doacao model) {
        DoacaoDTO dto = new DoacaoDTO();
        dto.setDescricao(model.getDescricao());
        dto.setTitulo(model.getTitulo());
        dto.setAnuncioId(model.getAnuncio().getId());
        dto.setDataHoraDoacao(model.getDataHoraDoacao());
        dto.setNomeUsuario(model.getUsuario().getNomeUsuario());
        return dto;
    }


    public static List<DoacaoDTO> convertListModelToListDto(List<Doacao> models) {
        List<DoacaoDTO> doacoes = new ArrayList<>();
        models.forEach(m -> {
            doacoes.add(convertModelToDto(m));
        });
        return doacoes;
    }

    public static Doacao convertDtoToModel(DoacaoDTO dto) {
        Doacao model = new Doacao();
        model.setDescricao(dto.getDescricao());
        model.setTitulo(dto.getTitulo());
        model.setDataHoraDoacao(dto.getDataHoraDoacao());
        return model;

    }
}
