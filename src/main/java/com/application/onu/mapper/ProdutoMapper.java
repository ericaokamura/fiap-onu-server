package com.application.onu.mapper;

import com.application.onu.model.Carrinho;
import com.application.onu.model.Produto;
import com.application.onu.model.dto.CarrinhoDTO;
import com.application.onu.model.dto.ProdutoDTO;

import java.util.ArrayList;
import java.util.List;

public class ProdutoMapper {

    public static ProdutoDTO convertModelToDto(Produto model) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setAnuncioId(model.getAnuncio().getId());
        dto.setCarrinhoId(model.getCarrinho().getId());
        dto.setDataHoraProdutoAdicionado(model.getDataHoraProdutoAdicionado());
        dto.setFotoAnuncio(model.getAnuncio().getFotoAnuncio());
        dto.setTitulo(model.getAnuncio().getTitulo());
        dto.setPreco(model.getAnuncio().getPreco());
        dto.setDescricao(model.getAnuncio().getDescricao());
        return dto;
    }

    public static Produto convertDtoToModel(ProdutoDTO dto) {
        Produto model = new Produto();
        model.setDataHoraProdutoAdicionado(dto.getDataHoraProdutoAdicionado());
        return model;
    }

    public static List<ProdutoDTO> convertListModelToListDto(List<Produto> models) {
        List<ProdutoDTO> dtos = new ArrayList<>();
        models.forEach(m -> {
            dtos.add(convertModelToDto(m));
        });
        return dtos;
    }


}

