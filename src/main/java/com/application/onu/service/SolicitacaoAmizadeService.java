package com.application.onu.service;

import com.application.onu.exception.SolicitacaoAmizadeJaExistenteException;
import com.application.onu.exception.SolicitacaoAmizadeNaoEncontradaException;
import com.application.onu.exception.UsuarioNaoEncontradoException;
import com.application.onu.mapper.SolicitacaoAmizadeMapper;
import com.application.onu.model.Perfil;
import com.application.onu.model.SolicitacaoAmizade;
import com.application.onu.model.Usuario;
import com.application.onu.model.dto.SolicitacaoAmizadeDTO;
import com.application.onu.repository.PerfilRepository;
import com.application.onu.repository.SolicitacaoAmizadeRepository;
import com.application.onu.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SolicitacaoAmizadeService {

    @Autowired
    private SolicitacaoAmizadeRepository solicitacaoAmizadeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;


    public SolicitacaoAmizadeDTO salvarSolicitacaoAmizade(SolicitacaoAmizadeDTO amizadeDTO) {
        Usuario solicita = null;
        Usuario recebeSolicitacao = null;
        Optional<Usuario> solicitante = usuarioRepository.findByNomeUsuario(amizadeDTO.getSolicitanteNomeUsuario());
        if(solicitante.isPresent()) {
            solicita = solicitante.get();
        } else {
            throw new UsuarioNaoEncontradoException("Usuario nao encontrado.");
        }
        Optional<Usuario> recebe = usuarioRepository.findByNomeUsuario(amizadeDTO.getRecebeNomeUsuario());
        if(recebe.isPresent()) {
            recebeSolicitacao = recebe.get();
        } else {
            throw new UsuarioNaoEncontradoException("Usuario nao encontrado.");
        }
        Optional<SolicitacaoAmizade> solicitacaoAmizadeOptional = solicitacaoAmizadeRepository.findAllByRecebeAndSolicitante(recebeSolicitacao, solicita);
        if(solicitacaoAmizadeOptional.isPresent()) {
            throw new SolicitacaoAmizadeJaExistenteException("Solicitacao de amizade já existente.");
        }
        SolicitacaoAmizade solicitacaoAmizade = new SolicitacaoAmizade();
        solicitacaoAmizade.setSolicitante(solicita);
        solicitacaoAmizade.setRecebe(recebeSolicitacao);
        solicitacaoAmizade.setAmizadeAprovada(amizadeDTO.isAmizadeAprovada());
        solicitacaoAmizade.setDataHoraSolicitacao(LocalDateTime.now());
        SolicitacaoAmizade retorno = solicitacaoAmizadeRepository.save(solicitacaoAmizade);
        return SolicitacaoAmizadeMapper.convertModelToDto(retorno);
    }

    public SolicitacaoAmizadeDTO aprovarSolicitacaoAmizade(SolicitacaoAmizadeDTO solicitacaoAmizadeDTO) {
        Usuario solicita = null;
        Usuario recebeSolicitacao = null;
        Optional<Usuario> solicitante = usuarioRepository.findByNomeUsuario(solicitacaoAmizadeDTO.getSolicitanteNomeUsuario());
        if(solicitante.isPresent()) {
            solicita = solicitante.get();
        } else {
            throw new UsuarioNaoEncontradoException("Usuario nao encontrado.");
        }
        Optional<Usuario> recebe = usuarioRepository.findByNomeUsuario(solicitacaoAmizadeDTO.getRecebeNomeUsuario());
        if(recebe.isPresent()) {
            recebeSolicitacao = recebe.get();
        } else {
            throw new UsuarioNaoEncontradoException("Usuario nao encontrado.");
        }
        Optional<SolicitacaoAmizade> solicitacaoAmizadeOptional = solicitacaoAmizadeRepository.findAllByRecebeAndSolicitante(recebeSolicitacao, solicita);
        if(!solicitacaoAmizadeOptional.isPresent()) {
            throw new SolicitacaoAmizadeNaoEncontradaException("Solicitacao de amizade nao encontrada.");
        }
        SolicitacaoAmizade solicitacaoAmizade = solicitacaoAmizadeOptional.get();
        solicitacaoAmizade.setAmizadeAprovada(true);
        solicitacaoAmizade.setDataHoraAprovacao(LocalDateTime.now());
        SolicitacaoAmizade retorno = solicitacaoAmizadeRepository.save(solicitacaoAmizade);
        return SolicitacaoAmizadeMapper.convertModelToDto(retorno);
    }

    public void deletarSolicitacaoAmizade(String solicitanteUsuario, String recebeUsuario) {
        Usuario solicita = null;
        Usuario recebeSolicitacao = null;
        Optional<Usuario> solicitante = usuarioRepository.findByNomeUsuario(solicitanteUsuario);
        if(solicitante.isPresent()) {
            solicita = solicitante.get();
        } else {
            throw new UsuarioNaoEncontradoException("Usuario nao encontrado.");
        }
        Optional<Usuario> recebe = usuarioRepository.findByNomeUsuario(recebeUsuario);
        if(recebe.isPresent()) {
            recebeSolicitacao = recebe.get();
        } else {
            throw new UsuarioNaoEncontradoException("Usuario nao encontrado.");
        }
        Optional<SolicitacaoAmizade> solicitacaoAmizadeOptional = solicitacaoAmizadeRepository.findAllByRecebeAndSolicitante(recebeSolicitacao, solicita);
        if(!solicitacaoAmizadeOptional.isPresent()) {
            throw new SolicitacaoAmizadeNaoEncontradaException("Solicitacao de amizade nao encontrada.");
        }
        SolicitacaoAmizade solicitacaoAmizade = solicitacaoAmizadeOptional.get();
        solicitacaoAmizade.setAmizadeAprovada(false);
        solicitacaoAmizade.setDataHoraDelecao(LocalDateTime.now());
        solicitacaoAmizadeRepository.save(solicitacaoAmizade);
    }

    public List<SolicitacaoAmizadeDTO> retornarTodasSolicitacoesAmizadePorUsuario(String recebeNomeUsuario) {
        Optional<Usuario> retorno = usuarioRepository.findByNomeUsuario(recebeNomeUsuario);
        if(retorno.isPresent()){
            List<SolicitacaoAmizade> solicitacoes = solicitacaoAmizadeRepository.findAllByRecebe(retorno.get());
            return SolicitacaoAmizadeMapper.convertListModelToListDto(solicitacoes);
        }
        throw new UsuarioNaoEncontradoException("Usuario nao encontrado.");
    }

    public SolicitacaoAmizadeDTO retornarSolicitacaoAmizade(String solicitanteUsuario, String recebeUsuario) {
        Optional<Usuario> solicitante = usuarioRepository.findByNomeUsuario(solicitanteUsuario);
        if(!solicitante.isPresent()){
            throw new UsuarioNaoEncontradoException("Usuario solicitante nao encontrado.");
        }
        Optional<Usuario> recebe = usuarioRepository.findByNomeUsuario(recebeUsuario);
        if(!recebe.isPresent()){
            throw new UsuarioNaoEncontradoException("Usuario que recebe solicitacao de amizade nao encontrado.");
        }
        Usuario usuarioSolicitante = solicitante.get();
        Usuario usuarioRecebe = recebe.get();

        Optional<SolicitacaoAmizade> solicitacao = solicitacaoAmizadeRepository.findAllByRecebeAndSolicitante(usuarioRecebe, usuarioSolicitante);
        if(!solicitacao.isPresent()) {
            throw new SolicitacaoAmizadeNaoEncontradaException("Solicitacao de amizade nao encontrada.");
        }
        return SolicitacaoAmizadeMapper.convertModelToDto(solicitacao.get());
    }

    public SolicitacaoAmizadeDTO atualizarSolicitacaoAmizade(SolicitacaoAmizadeDTO solicitacaoDTO) {
        Usuario solicita = null;
        Usuario recebeSolicitacao = null;
        Optional<Usuario> solicitante = usuarioRepository.findByNomeUsuario(solicitacaoDTO.getSolicitanteNomeUsuario());
        if(solicitante.isPresent()) {
            solicita = solicitante.get();
        } else {
            throw new UsuarioNaoEncontradoException("Usuario nao encontrado.");
        }
        Optional<Usuario> recebe = usuarioRepository.findByNomeUsuario(solicitacaoDTO.getRecebeNomeUsuario());
        if(recebe.isPresent()) {
            recebeSolicitacao = recebe.get();
        } else {
            throw new UsuarioNaoEncontradoException("Usuario nao encontrado.");
        }
        Optional<SolicitacaoAmizade> solicitacaoAmizadeOptional = solicitacaoAmizadeRepository.findAllByRecebeAndSolicitante(recebeSolicitacao, solicita);
        if(!solicitacaoAmizadeOptional.isPresent()) {
            throw new SolicitacaoAmizadeNaoEncontradaException("Solicitacao de amizade nao encontrada.");
        }
        SolicitacaoAmizade solicitacaoAmizade = solicitacaoAmizadeOptional.get();
        solicitacaoAmizade.setAmizadeAprovada(solicitacaoDTO.isAmizadeAprovada());
        solicitacaoAmizade.setDataHoraSolicitacao(solicitacaoDTO.getDataHoraSolicitacao());
        solicitacaoAmizade.setDataHoraDelecao(solicitacaoDTO.getDataHoraDelecao());
        solicitacaoAmizade.setDataHoraAprovacao(solicitacaoDTO.getDataHoraAprovacao());
        SolicitacaoAmizade retorno = solicitacaoAmizadeRepository.save(solicitacaoAmizade);
        return SolicitacaoAmizadeMapper.convertModelToDto(retorno);
    }
}
