package com.application.onu.service;

import com.application.onu.exception.AnuncioNaoEncontradoException;
import com.application.onu.exception.UsuarioNaoEncontradoException;
import com.application.onu.mapper.RecebimentoMapper;
import com.application.onu.model.Anuncio;
import com.application.onu.model.Recebimento;
import com.application.onu.model.Usuario;
import com.application.onu.model.dto.RecebimentoDTO;
import com.application.onu.repository.AnuncioRepository;
import com.application.onu.repository.RecebimentoRepository;
import com.application.onu.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RecebimentoService {

    @Autowired
    private RecebimentoRepository recebimentoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AnuncioRepository anuncioRepository;

    public RecebimentoDTO salvarRecebimento(RecebimentoDTO dto) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNomeUsuario(dto.getNomeUsuario());
        Optional<Anuncio> anuncioOptional = anuncioRepository.findById(dto.getAnuncioId());
        if(!anuncioOptional.isPresent()) {
            throw new AnuncioNaoEncontradoException("Anuncio nao encontrado.");
        }
        if(usuarioOptional.isPresent()) {
            Recebimento recebimento = RecebimentoMapper.convertDtoToModel(dto);
            recebimento.setDataHoraRecebimento(LocalDateTime.now());
            recebimento.setUsuario(usuarioOptional.get());
            recebimento.setAnuncio(anuncioOptional.get());
            Recebimento retorno = recebimentoRepository.save(recebimento);
            return RecebimentoMapper.convertModelToDto(retorno);
        }
        throw new UsuarioNaoEncontradoException("Usuario nao encontrado.");
    }

    public List<RecebimentoDTO> retornarTodosRecebimentosPorUsuario(String nomeUsuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNomeUsuario(nomeUsuario);
        if(usuarioOptional.isPresent()){
            List<Recebimento> recebimentos = recebimentoRepository.findAllByUsuario(usuarioOptional.get());
            return RecebimentoMapper.convertListModelToListDto(recebimentos);
        }
        throw new UsuarioNaoEncontradoException("Usuario nao encontrado.");
    }
}
