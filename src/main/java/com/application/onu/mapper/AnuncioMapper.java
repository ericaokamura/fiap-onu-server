package com.application.onu.mapper;

import com.application.onu.model.Anuncio;
import com.application.onu.model.Doacao;
import com.application.onu.model.dto.AnuncioDTO;
import com.application.onu.model.dto.DoacaoDTO;

import java.util.ArrayList;
import java.util.List;

public class AnuncioMapper {

    public static AnuncioDTO convertModelToDto(Anuncio model) {
        AnuncioDTO dto = new AnuncioDTO();
        dto.setId(model.getId());
        dto.setDescricao(model.getDescricao());
        dto.setPreco(model.getPreco());
        dto.setTipoAnuncio(model.getTipoAnuncio());
        dto.setTitulo(model.getTitulo());
        dto.setFotoAnuncio(model.getFotoAnuncio());
        dto.setDisponibilidade(model.isDisponibilidade());
        dto.setDataHoraPublicacao(model.getDataHoraPublicacao());
        dto.setNomeUsuario(model.getUsuario().getNomeUsuario());
        return dto;
    }

    public static Anuncio convertDtoToModel(AnuncioDTO dto) {
        Anuncio model = new Anuncio();
        model.setId(dto.getId());
        model.setDescricao(dto.getDescricao());
        model.setPreco(dto.getPreco());
        model.setTipoAnuncio(dto.getTipoAnuncio());
        model.setTitulo(dto.getTitulo());
        model.setFotoAnuncio(dto.getFotoAnuncio());
        model.setDisponibilidade(dto.isDisponibilidade());
        model.setDataHoraPublicacao(dto.getDataHoraPublicacao());
        return model;
    }


    public static List<AnuncioDTO> convertListModelToListDto(List<Anuncio> models) {
        List<AnuncioDTO> anuncios = new ArrayList<>();
        models.forEach(m -> {
            anuncios.add(convertModelToDto(m));
        });
        return anuncios;
    }

}
