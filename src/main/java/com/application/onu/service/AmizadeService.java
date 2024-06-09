package com.application.onu.service;

import com.application.onu.exception.AmizadeNaoEncontradaException;
import com.application.onu.exception.PerfilNaoEncontradoException;
import com.application.onu.exception.UsuarioNaoEncontradoException;
import com.application.onu.mapper.AmizadeMapper;
import com.application.onu.model.Amizade;
import com.application.onu.model.Perfil;
import com.application.onu.model.Usuario;
import com.application.onu.model.dto.AmizadeDTO;
import com.application.onu.repository.AmizadeRepository;
import com.application.onu.repository.PerfilRepository;
import com.application.onu.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AmizadeService {

    @Autowired
    private AmizadeRepository amizadeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    // nova amizade
    public AmizadeDTO salvarAmizade(AmizadeDTO amizadeDTO) {
        Usuario seguido = verificaExistenciaUsuario(amizadeDTO.getSeguidoNomeUsuario());
        Usuario seguidor = verificaExistenciaUsuario(amizadeDTO.getSeguidorNomeUsuario());
        Optional<Amizade> amizadeOptional = amizadeRepository.findBySeguidorAndSeguido(seguidor, seguido);
        if(amizadeOptional.isPresent()) {
            Amizade amizade = amizadeOptional.get();
            amizade.setAmigos(true);
            amizade.setDataHoraInicio(LocalDateTime.now());
            AmizadeDTO retorno = AmizadeMapper.convertModelToDto(amizadeRepository.save(amizade));
            verificaExistenciaPerfisAdicao(amizade);
            return retorno;
        } else  {
            Amizade amizade = new Amizade();
            amizade.setAmigos(true);
            amizade.setDataHoraInicio(LocalDateTime.now());
            amizade.setSeguido(seguido);
            amizade.setSeguidor(seguidor);
            AmizadeDTO retorno = AmizadeMapper.convertModelToDto(amizadeRepository.save(amizade));
            verificaExistenciaPerfisAdicao(amizade);
            return retorno;
        }
    }

    // remover amizade
    public AmizadeDTO removerAmizade(AmizadeDTO amizadeDTO) {
        Usuario seguido = verificaExistenciaUsuario(amizadeDTO.getSeguidoNomeUsuario());
        Usuario seguidor = verificaExistenciaUsuario(amizadeDTO.getSeguidorNomeUsuario());
        Optional<Amizade> amizadeOptional = amizadeRepository.findBySeguidorAndSeguido(seguidor, seguido);
        if(amizadeOptional.isPresent()) {
            Amizade amizade = amizadeOptional.get();
            amizade.setAmigos(false);
            amizade.setDataHoraFim(LocalDateTime.now());
            AmizadeDTO retorno = AmizadeMapper.convertModelToDto(amizadeRepository.save(amizade));
            verificaExistenciaPerfisRemocao(amizade);
            return retorno;
        }
        throw new AmizadeNaoEncontradaException("Amizade nao encontrada.");
    }

    private void verificaExistenciaPerfisAdicao(Amizade amizade) {
        Optional<Perfil> perfilSeguidorOptional = perfilRepository.findByUsuario(amizade.getSeguidor());
        if(perfilSeguidorOptional.isPresent()){
            Perfil perfilSeguidor = perfilSeguidorOptional.get();
            perfilSeguidor.setNumeroSeguindo(perfilSeguidor.getNumeroSeguindo()+1);
            perfilRepository.save(perfilSeguidor);
        } else {
            throw new PerfilNaoEncontradoException("Perfil nao encontrado.");
        }
        Optional<Perfil> perfilSeguidoOptional = perfilRepository.findByUsuario(amizade.getSeguido());
        if(perfilSeguidoOptional.isPresent()){
            Perfil perfilSeguido = perfilSeguidoOptional.get();
            perfilSeguido.setNumeroSeguidores(perfilSeguido.getNumeroSeguidores()+1);
            perfilRepository.save(perfilSeguido);
        } else {
            throw new PerfilNaoEncontradoException("Perfil nao encontrado.");
        }
    }

    private void verificaExistenciaPerfisRemocao(Amizade amizade) {
        Optional<Perfil> perfilSeguidorOptional = perfilRepository.findByUsuario(amizade.getSeguidor());
        if(perfilSeguidorOptional.isPresent()){
            Perfil perfilSeguidor = perfilSeguidorOptional.get();
            perfilSeguidor.setNumeroSeguindo(perfilSeguidor.getNumeroSeguindo()-1);
            perfilRepository.save(perfilSeguidor);
        } else {
            throw new PerfilNaoEncontradoException("Perfil nao encontrado.");
        }
        Optional<Perfil> perfilSeguidoOptional = perfilRepository.findByUsuario(amizade.getSeguido());
        if(perfilSeguidoOptional.isPresent()){
            Perfil perfilSeguido = perfilSeguidoOptional.get();
            perfilSeguido.setNumeroSeguidores(perfilSeguido.getNumeroSeguidores()-1);
            perfilRepository.save(perfilSeguido);
        } else {
            throw new PerfilNaoEncontradoException("Perfil nao encontrado.");
        }
    }

    private Usuario verificaExistenciaUsuario(String nomeUsuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNomeUsuario(nomeUsuario);
        if (!usuarioOptional.isPresent()) {
            throw new UsuarioNaoEncontradoException("Usuario nao encontrado.");
        }
        return usuarioOptional.get();
    }

    public AmizadeDTO verificarExistenciaAmizade(String nomeSeguidor, String nomeSeguido) {
        Usuario seguido = verificaExistenciaUsuario(nomeSeguido);
        Usuario seguidor = verificaExistenciaUsuario(nomeSeguidor);
        Optional<Amizade> amizadeOptional = amizadeRepository.findBySeguidorAndSeguido(seguidor, seguido);
        if (!amizadeOptional.isPresent()) {
            throw new AmizadeNaoEncontradaException("Amizade nao encontrada.");
        }
        return AmizadeMapper.convertModelToDto(amizadeOptional.get());
    }

}


