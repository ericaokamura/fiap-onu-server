package com.application.onu.service;

import com.application.onu.exception.PerfilNaoEncontradoException;
import com.application.onu.exception.UsuarioJaExistenteException;
import com.application.onu.exception.UsuarioNaoEncontradoException;
import com.application.onu.mapper.UsuarioMapper;
import com.application.onu.model.Carrinho;
import com.application.onu.model.Configuracao;
import com.application.onu.model.Perfil;
import com.application.onu.model.Usuario;
import com.application.onu.model.dto.ConfiguracaoDTO;
import com.application.onu.model.dto.UsuarioDTO;
import com.application.onu.repository.CarrinhoRepository;
import com.application.onu.repository.ConfiguracaoRepository;
import com.application.onu.repository.PerfilRepository;
import com.application.onu.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private PerfilRepository perfilRepository;
    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioDTO retornarUsuarioPorId(Long usuarioId) {
        Optional<Usuario> retorno = usuarioRepository.findById(usuarioId);
        if(retorno.isPresent()) {
            Usuario usuario = retorno.get();
            usuario.setSenha("");
            return UsuarioMapper.convertModelToDto(usuario);
        }
        throw new UsuarioNaoEncontradoException("Usuario nao encontrado.");
    }

    public UsuarioDTO retornarUsuarioPorNome(String nomeUsuario) {
        Optional<Usuario> retorno = usuarioRepository.findByNomeUsuario(nomeUsuario);
        if(retorno.isPresent()) {
            Usuario usuario = retorno.get();
            return UsuarioMapper.convertModelToDto(usuario);
        }
        throw new UsuarioNaoEncontradoException("Usuario nao encontrado.");
    }

    public List<UsuarioDTO> retornarTodosUsuarios() {
        List<Usuario> retorno = usuarioRepository.findAll();
        return UsuarioMapper.convertListModelToListDto(retorno);
    }


    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDto) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNomeUsuario(usuarioDto.getNomeUsuario());
        if(usuarioOptional.isPresent()) {
            throw new UsuarioJaExistenteException("Este nome de usuário já existe.");
        }
        Usuario usuario = UsuarioMapper.convertDtoToModel(usuarioDto);
        usuario.setSenha(passwordEncoder.encode(usuarioDto.getSenha()));
        usuario.setUltimaAtualizacao(LocalDateTime.now());
        Usuario retorno = usuarioRepository.save(usuario);
        Carrinho carrinho = new Carrinho();
        carrinho.setUsuario(retorno);
        carrinhoRepository.save(carrinho);
        Perfil perfil = new Perfil();
        perfil.setUsuario(retorno);
        perfil.setNumeroAnuncios(0L);
        perfil.setNumeroSeguindo(0L);
        perfil.setNumeroSeguidores(0L);
        perfil.setFotoPerfil(usuarioDto.getFotoPerfil());
        perfil.setDataCadastro(LocalDate.now());
        perfilRepository.save(perfil);
        Configuracao configuracao = new Configuracao();
        configuracao.setUsuario(retorno);
        configuracao.setAcessibilidade(false);
        configuracao.setPerfilPublico(true);
        configuracao.setUltimaAtualizacao(LocalDateTime.now());
        configuracaoRepository.save(configuracao);
        retorno.setSenha("");
        UsuarioDTO retornoDto = UsuarioMapper.convertModelToDto(usuario);
        return retornoDto;
    }

    public UsuarioDTO atualizarUsuario(UsuarioDTO usuarioDto) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNomeUsuario(usuarioDto.getNomeUsuario());
        if(usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            Optional<Perfil> perfilOptional = perfilRepository.findByUsuario(usuario);
            if(!perfilOptional.isPresent()) {
                throw new PerfilNaoEncontradoException("Perfil nao encontrado.");
            }
            Perfil perfil = perfilOptional.get();
            perfil.setFotoPerfil(usuarioDto.getFotoPerfil());
            perfilRepository.save(perfil);
            usuario.setSenha(passwordEncoder.encode(usuarioDto.getSenha()));
            usuario.setEmail(usuarioDto.getEmail());
            usuario.setFotoPerfil(usuarioDto.getFotoPerfil());
            usuario.setUltimaAtualizacao(LocalDateTime.now());
            usuario.setNomeCompleto(usuarioDto.getNomeCompleto());
            Usuario retorno = usuarioRepository.save(usuario);
            return UsuarioMapper.convertModelToDto(retorno);
        }
        throw new UsuarioNaoEncontradoException("Usuario nao encontrado.");
    }

}
