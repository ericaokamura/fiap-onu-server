package com.application.onu.mapper;

import com.application.onu.model.Carrinho;
import com.application.onu.model.Doacao;
import com.application.onu.model.dto.AmizadeDTO;
import com.application.onu.model.dto.CarrinhoDTO;
import com.application.onu.model.dto.DoacaoDTO;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoMapper {

    public static CarrinhoDTO convertModelToDto(Carrinho model) {
        CarrinhoDTO dto = new CarrinhoDTO();
        dto.setCarrinhoId(model.getId());
        dto.setNomeUsuario(model.getUsuario().getNomeUsuario());
        return dto;
    }

    public static List<CarrinhoDTO> convertListModelToListDto(List<Carrinho> models) {
        List<CarrinhoDTO> carrinhos = new ArrayList<>();
        models.forEach(m -> {
            carrinhos.add(convertModelToDto(m));
        });
        return carrinhos;
    }
}

