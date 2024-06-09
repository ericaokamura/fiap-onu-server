package com.application.onu.service;

import com.application.onu.exception.AnuncioNaoEncontradoException;
import com.application.onu.exception.UsuarioNaoEncontradoException;
import com.application.onu.mapper.CompraMapper;
import com.application.onu.model.Anuncio;
import com.application.onu.model.Compra;
import com.application.onu.model.Usuario;
import com.application.onu.model.dto.CompraDTO;
import com.application.onu.repository.AnuncioRepository;
import com.application.onu.repository.CompraRepository;
import com.application.onu.repository.PerfilRepository;
import com.application.onu.repository.UsuarioRepository;
import org.hibernate.boot.jaxb.internal.stax.LocalSchemaLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AnuncioRepository anuncioRepository;

    public List<CompraDTO> retornarComprasPorUsuario(String nomeUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findByNomeUsuario(nomeUsuario);
        if(usuario.isPresent()){
            List<Compra> compras = compraRepository.findAllByUsuario(usuario.get());
            return CompraMapper.convertListModelToListDto(compras);
        }
        throw new UsuarioNaoEncontradoException("Usuario nao encontrado.");
    }

    public CompraDTO salvarCompra(CompraDTO compraDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNomeUsuario(compraDTO.getNomeUsuario());
        Optional<Anuncio> optionalAnuncio = anuncioRepository.findById(compraDTO.getAnuncioId());
        if(usuarioOptional.isPresent()) {
            if(optionalAnuncio.isPresent()) {
                Compra compra = CompraMapper.convertDtoToModel(compraDTO);
                compra.setUsuario(usuarioOptional.get());
                compra.setAnuncio(optionalAnuncio.get());
                compra.setDataHoraCompra(LocalDateTime.now());
                Compra retorno = compraRepository.save(compra);
                return CompraMapper.convertModelToDto(retorno);
            }
            throw new AnuncioNaoEncontradoException("Anuncio nao encontrado.");
        }
        throw new UsuarioNaoEncontradoException("Usuario nao encontrado.");
    }
}
