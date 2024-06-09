package com.application.onu.service;


import com.application.onu.exception.UsuarioNaoEncontradoException;
import com.application.onu.mapper.ConfiguracaoMapper;
import com.application.onu.model.Configuracao;
import com.application.onu.model.Usuario;
import com.application.onu.model.dto.ConfiguracaoDTO;
import com.application.onu.repository.ConfiguracaoRepository;
import com.application.onu.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConfiguracaoService {

    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ConfiguracaoDTO atualizarConfiguracao(ConfiguracaoDTO configuracaoDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNomeUsuario(configuracaoDTO.getNomeUsuario());
        if(!usuarioOptional.isPresent()) {
            throw new UsuarioNaoEncontradoException("Usuario nao encontrado.");
        }
        Configuracao configuracao = configuracaoRepository.findByUsuario(usuarioOptional.get());
        configuracao.setPerfilPublico(configuracaoDTO.isPerfilPublico());
        configuracao.setAcessibilidade(configuracao.isAcessibilidade());
        configuracao.setUltimaAtualizacao(LocalDateTime.now());
        return ConfiguracaoMapper.convertModelToDto(configuracaoRepository.save(configuracao));
    }

    public ConfiguracaoDTO retornarConfiguracao(String nomeUsuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNomeUsuario(nomeUsuario);
        if(!usuarioOptional.isPresent()) {
            throw new UsuarioNaoEncontradoException("Usuario nao encontrado.");
        }
        return ConfiguracaoMapper.convertModelToDto(configuracaoRepository.findByUsuario(usuarioOptional.get()));
    }
}
