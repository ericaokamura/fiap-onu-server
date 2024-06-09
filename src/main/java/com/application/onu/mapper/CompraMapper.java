package com.application.onu.mapper;

import com.application.onu.model.Compra;
import com.application.onu.model.Doacao;
import com.application.onu.model.dto.CompraDTO;
import com.application.onu.model.dto.DoacaoDTO;
import com.application.onu.model.dto.RecebimentoDTO;

import java.util.ArrayList;
import java.util.List;

public class CompraMapper {

    public static CompraDTO convertModelToDto(Compra model) {
        CompraDTO dto = new CompraDTO();
        dto.setDescricao(model.getDescricao());
        dto.setTitulo(model.getTitulo());
        dto.setPreco(model.getPreco());
        dto.setNomeUsuario(model.getUsuario().getNomeUsuario());
        dto.setAnuncioId(model.getAnuncio().getId());
        dto.setDataHoraCompra(model.getDataHoraCompra());
        return dto;
    }


    public static List<CompraDTO> convertListModelToListDto(List<Compra> models) {
        List<CompraDTO> compras = new ArrayList<>();
        models.forEach(m -> {
            compras.add(convertModelToDto(m));
        });
        return compras;
    }

    public static Compra convertDtoToModel(CompraDTO dto) {
        Compra model = new Compra();
        model.setDescricao(dto.getDescricao());
        model.setTitulo(dto.getTitulo());
        model.setPreco(dto.getPreco());
        model.setDataHoraCompra(dto.getDataHoraCompra());
        return model;

    }
}
