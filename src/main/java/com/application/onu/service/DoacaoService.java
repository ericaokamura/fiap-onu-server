package com.application.onu.service;

import com.application.onu.exception.AnuncioNaoEncontradoException;
import com.application.onu.exception.UsuarioNaoEncontradoException;
import com.application.onu.mapper.DoacaoMapper;
import com.application.onu.model.Anuncio;
import com.application.onu.model.Doacao;
import com.application.onu.model.Usuario;
import com.application.onu.model.dto.DoacaoDTO;
import com.application.onu.repository.AnuncioRepository;
import com.application.onu.repository.DoacaoRepository;
import com.application.onu.repository.PerfilRepository;
import com.application.onu.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DoacaoService {

    @Autowired
    private DoacaoRepository doacaoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PerfilRepository perfilRepository;
    @Autowired
    private AnuncioRepository anuncioRepository;

    public List<DoacaoDTO> retornarTodasDoacoesPorUsuario(String nomeUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findByNomeUsuario(nomeUsuario);
        if(usuario.isPresent()){
            List<Doacao> doacoes = doacaoRepository.findAllByUsuario(usuario.get());
            return DoacaoMapper.convertListModelToListDto(doacoes);
        }
        throw new UsuarioNaoEncontradoException("Usuario nao encontrado.");
    }

    public DoacaoDTO salvarDoacao(DoacaoDTO doacaoDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNomeUsuario(doacaoDTO.getNomeUsuario());
        Optional<Anuncio> anuncioOptional = anuncioRepository.findById(doacaoDTO.getAnuncioId());
        if(!anuncioOptional.isPresent()) {
            throw new AnuncioNaoEncontradoException("Anuncio nao encontrado.");
        }
        if(usuarioOptional.isPresent()) {
            Doacao doacao = DoacaoMapper.convertDtoToModel(doacaoDTO);
            doacao.setDataHoraDoacao(LocalDateTime.now());
            doacao.setAnuncio(anuncioOptional.get());
            doacao.setUsuario(usuarioOptional.get());
            Doacao retorno = doacaoRepository.save(doacao);
            return DoacaoMapper.convertModelToDto(retorno);
        }
        throw new UsuarioNaoEncontradoException("Usuario nao encontrado.");
    }
}
