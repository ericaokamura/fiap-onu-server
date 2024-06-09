package com.application.onu.service;


import com.application.onu.exception.UsuarioNaoEncontradoException;
import com.application.onu.mapper.PerfilMapper;
import com.application.onu.model.Perfil;
import com.application.onu.model.Usuario;
import com.application.onu.model.dto.PerfilDTO;
import com.application.onu.repository.PerfilRepository;
import com.application.onu.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public PerfilDTO atualizarPerfil(PerfilDTO perfilDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNomeUsuario(perfilDTO.getNomeUsuario());
        if(usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            Optional<Perfil> perfilOptional = perfilRepository.findByUsuario(usuario);
            if(perfilOptional.isPresent()) {
                Perfil perfil = perfilOptional.get();
                perfil.setFotoPerfil(perfilDTO.getFotoPerfil());
                Perfil retorno = perfilRepository.save(perfil);
                return PerfilMapper.convertModelToDto(retorno);
            }
        }
        throw new UsuarioNaoEncontradoException("Usuario nao encontrado.");
    }

    public PerfilDTO retornarPerfil(String nomeUsuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNomeUsuario(nomeUsuario);
        if(usuarioOptional.isPresent()){
            Optional<Perfil> perfilOptional = perfilRepository.findByUsuario(usuarioOptional.get());
            if(perfilOptional.isPresent()){
                Perfil perfil = perfilOptional.get();
                return PerfilMapper.convertModelToDto(perfil);
            }
        }
        throw new UsuarioNaoEncontradoException("Usuario nao encontrado.");
    }
}
