package com.application.onu.service;

import com.application.onu.exception.AnuncioNaoEncontradoException;
import com.application.onu.exception.PerfilNaoEncontradoException;
import com.application.onu.exception.UsuarioNaoEncontradoException;
import com.application.onu.mapper.AnuncioMapper;
import com.application.onu.model.Anuncio;
import com.application.onu.model.Perfil;
import com.application.onu.model.Usuario;
import com.application.onu.model.dto.AnuncioDTO;
import com.application.onu.repository.AnuncioRepository;
import com.application.onu.repository.PerfilRepository;
import com.application.onu.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class AnuncioService {

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PerfilRepository perfilRepository;


    public List<AnuncioDTO> retornarTodosAnuncios() {
        return AnuncioMapper.convertListModelToListDto(anuncioRepository.findAll());
    }

    public List<AnuncioDTO> retornarTodosAnunciosPorUsuario(String nomeUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findByNomeUsuario(nomeUsuario);
        if(usuario.isPresent()) {
            List<Anuncio> anuncios = anuncioRepository.findAllByUsuario(usuario.get());
            return AnuncioMapper.convertListModelToListDto(anuncios);
        }
        throw new UsuarioNaoEncontradoException("Usuario nao encontrado.");
    }

    public AnuncioDTO salvarAnuncio(AnuncioDTO anuncioDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNomeUsuario(anuncioDTO.getNomeUsuario());
        if(!usuarioOptional.isPresent()) {
            throw new UsuarioNaoEncontradoException("Usuario nao encontrado.");
        }
        Optional<Perfil> perfilOptional = perfilRepository.findByUsuario(usuarioOptional.isPresent() ? usuarioOptional.get() : new Usuario());
        if(!perfilOptional.isPresent()){
            throw new PerfilNaoEncontradoException("Perfil nao encontrado.");
        }
        Perfil perfil = perfilOptional.get();
        perfil.setNumeroAnuncios(perfil.getNumeroAnuncios()+1);
        perfilRepository.save(perfil);
        Anuncio anuncio = AnuncioMapper.convertDtoToModel(anuncioDTO);
        anuncio.setUsuario(usuarioOptional.get());
        Anuncio retorno = anuncioRepository.save(anuncio);
        return AnuncioMapper.convertModelToDto(retorno);
    }

    public AnuncioDTO atualizarAnuncio(AnuncioDTO anuncioDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNomeUsuario(anuncioDTO.getNomeUsuario());
        if(!usuarioOptional.isPresent()) {
            throw new UsuarioNaoEncontradoException("Usuario nao encontrado.");
        }
        Optional<Perfil> perfilOptional = perfilRepository.findByUsuario(usuarioOptional.isPresent() ? usuarioOptional.get() : new Usuario());
        if(!perfilOptional.isPresent()){
            throw new PerfilNaoEncontradoException("Perfil nao encontrado.");
        }
        Perfil perfil = perfilOptional.get();
        perfil.setNumeroAnuncios(perfil.getNumeroAnuncios()+1);
        perfilRepository.save(perfil);
        Anuncio anuncio = AnuncioMapper.convertDtoToModel(anuncioDTO);
        anuncio.setUsuario(usuarioOptional.get());
        Anuncio retorno = anuncioRepository.save(anuncio);
        return AnuncioMapper.convertModelToDto(retorno);
    }

    public List<AnuncioDTO> retornarAnunciosPorTitulo(String titulo) {
        List<Anuncio> anuncios = anuncioRepository.findAll();
        List<Anuncio> retorno = new ArrayList<>();
        for (Anuncio anuncio : anuncios) {
            if(Pattern.compile(Pattern.quote(titulo), Pattern.CASE_INSENSITIVE).matcher(anuncio.getTitulo()).find()) {
                retorno.add(anuncio);
            }
        }
        return AnuncioMapper.convertListModelToListDto(retorno);
    }

    public AnuncioDTO retornarAnuncioPorId(Long anuncioId) {
        Optional<Anuncio> optionalAnuncio = anuncioRepository.findById(anuncioId);
        if(optionalAnuncio.isPresent()) {
            return AnuncioMapper.convertModelToDto(optionalAnuncio.get());
        }
        throw new AnuncioNaoEncontradoException("Anuncio nao encontrado.");
    }

    public List<AnuncioDTO> ordenarAnunciosPorPreco() {
        return AnuncioMapper.convertListModelToListDto(anuncioRepository.findAll(Sort.by(Sort.Direction.ASC, "preco")));
    }

    public List<AnuncioDTO> ordenarAnunciosPorDataHoraPublicacao() {
        return AnuncioMapper.convertListModelToListDto(anuncioRepository.findAll(Sort.by(Sort.Direction.DESC, "dataHoraPublicacao")));
    }
}
