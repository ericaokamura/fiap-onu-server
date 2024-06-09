package com.application.onu.service;

import com.application.onu.exception.AnuncioNaoEncontradoException;
import com.application.onu.exception.UsuarioNaoEncontradoException;
import com.application.onu.mapper.VendaMapper;
import com.application.onu.model.Anuncio;
import com.application.onu.model.Usuario;
import com.application.onu.model.Venda;
import com.application.onu.model.dto.VendaDTO;
import com.application.onu.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    public List<VendaDTO> retornarTodasVendasPorUsuario(String nomeUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findByNomeUsuario(nomeUsuario);
        if(usuario.isPresent()){
            List<Venda> vendas = vendaRepository.findAllByUsuario(usuario.get());
            return VendaMapper.convertListModelToListDto(vendas);
        }
        throw new UsuarioNaoEncontradoException("Usuario nao encontrado.");
    }

    public VendaDTO salvarVenda(VendaDTO vendaDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNomeUsuario(vendaDTO.getNomeUsuario());
        if(usuarioOptional.isPresent()) {
            Optional<Anuncio> anuncioOptional = anuncioRepository.findById(vendaDTO.getAnuncioId());
            if(anuncioOptional.isPresent()) {
                    Anuncio anuncio = anuncioOptional.get();
                    Venda venda = new Venda();
                    venda.setTitulo(vendaDTO.getTitulo());
                    venda.setDescricao(vendaDTO.getDescricao());
                    venda.setPreco(vendaDTO.getPreco());
                    venda.setDataHoraVenda(LocalDateTime.now());
                    venda.setUsuario(usuarioOptional.get());
                    venda.setAnuncio(anuncio);
                    Venda retorno = vendaRepository.save(venda);
                    return VendaMapper.convertModelToDto(retorno);
            }
            throw new AnuncioNaoEncontradoException("Anuncio nao encontrado.");
        }
        throw new UsuarioNaoEncontradoException("Usuario nao encontrado.");
    }
}
