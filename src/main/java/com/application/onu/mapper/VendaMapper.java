package com.application.onu.mapper;

import com.application.onu.model.Compra;
import com.application.onu.model.Venda;
import com.application.onu.model.dto.CompraDTO;
import com.application.onu.model.dto.VendaDTO;

import java.util.ArrayList;
import java.util.List;

public class VendaMapper {

    public static VendaDTO convertModelToDto(Venda model) {
        VendaDTO dto = new VendaDTO();
        dto.setTitulo(model.getTitulo());
        dto.setDescricao(model.getDescricao());
        dto.setAnuncioId(model.getAnuncio().getId());
        dto.setNomeUsuario(model.getUsuario().getNomeUsuario());
        dto.setPreco(model.getPreco());
        dto.setDataHoraVenda(model.getDataHoraVenda());
        return dto;
    }

    public static List<VendaDTO> convertListModelToListDto(List<Venda> models) {
        List<VendaDTO> vendas = new ArrayList<>();
        models.forEach(m -> {
            vendas.add(convertModelToDto(m));
        });
        return vendas;
    }
}

