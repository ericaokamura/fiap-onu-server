package com.application.onu.mapper;

import com.application.onu.model.Perfil;
import com.application.onu.model.dto.PerfilDTO;

import java.util.ArrayList;
import java.util.List;

public class PerfilMapper {

    public static PerfilDTO convertModelToDto(Perfil model) {
        PerfilDTO dto = new PerfilDTO();
        dto.setFotoPerfil(model.getFotoPerfil());
        dto.setDataCadastro(model.getDataCadastro());
        dto.setNomeUsuario(model.getUsuario().getNomeUsuario());
        dto.setNumeroSeguindo(model.getNumeroSeguindo());
        dto.setNumeroSeguidores(model.getNumeroSeguidores());
        dto.setNumeroAnuncios(model.getNumeroAnuncios());
        return dto;
    }

    public static Perfil convertDtoToModel(PerfilDTO dto) {
        Perfil model = new Perfil();
        model.setFotoPerfil(dto.getFotoPerfil());
        model.setDataCadastro(dto.getDataCadastro());
        model.setNumeroSeguindo(dto.getNumeroSeguindo());
        model.setNumeroSeguidores(dto.getNumeroSeguidores());
        model.setNumeroAnuncios(dto.getNumeroAnuncios());
        return model;
    }

    public static List<PerfilDTO> convertListModelToListDto(List<Perfil> models) {
        List<PerfilDTO> perfis = new ArrayList<>();
        models.forEach(m -> {
            perfis.add(convertModelToDto(m));
        });
        return perfis;
    }

}

