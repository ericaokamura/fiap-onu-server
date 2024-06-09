package com.application.onu.mapper;

import com.application.onu.model.Doacao;
import com.application.onu.model.Usuario;
import com.application.onu.model.dto.DoacaoDTO;
import com.application.onu.model.dto.UsuarioDTO;

import java.util.ArrayList;
import java.util.List;

public class UsuarioMapper {

    public static UsuarioDTO convertModelToDto(Usuario model) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNomeUsuario(model.getNomeUsuario());
        dto.setEmail(model.getEmail());
        dto.setSenha(model.getSenha());
        dto.setNomeCompleto(model.getNomeCompleto());
        dto.setUltimaAtualizacao(model.getUltimaAtualizacao());
        dto.setFotoPerfil(model.getFotoPerfil());
        return dto;
    }

    public static Usuario convertDtoToModel(UsuarioDTO dto) {
        Usuario model = new Usuario();
        model.setEmail(dto.getEmail());
        model.setNomeUsuario(dto.getNomeUsuario());
        model.setSenha(dto.getSenha());
        model.setUltimaAtualizacao(dto.getUltimaAtualizacao());
        model.setNomeCompleto(dto.getNomeCompleto());
        model.setFotoPerfil(dto.getFotoPerfil());
        return model;
    }

    public static List<Usuario> convertListDtoToListModel(List<UsuarioDTO> dtos) {
        List<Usuario> usuarios = new ArrayList<>();
        dtos.forEach(d -> {
            usuarios.add(convertDtoToModel(d));
        });
        return usuarios;
    }

    public static List<UsuarioDTO> convertListModelToListDto(List<Usuario> models) {
        List<UsuarioDTO> usuarios = new ArrayList<>();
        models.forEach(m -> {
            usuarios.add(convertModelToDto(m));
        });
        return usuarios;
    }


}
