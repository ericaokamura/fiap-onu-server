package com.application.onu.service;

import com.application.onu.exception.CarrinhoNaoEncontradoException;
import com.application.onu.exception.UsuarioNaoEncontradoException;
import com.application.onu.mapper.CarrinhoMapper;
import com.application.onu.model.Carrinho;
import com.application.onu.model.Usuario;
import com.application.onu.model.dto.CarrinhoDTO;
import com.application.onu.repository.CarrinhoRepository;
import com.application.onu.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public CarrinhoDTO retornarCarrinhoPorUsuario(String nomeUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findByNomeUsuario(nomeUsuario);
        if(usuario.isPresent()) {
            Carrinho retorno = carrinhoRepository.findByUsuario(usuario.get());
            return CarrinhoMapper.convertModelToDto(retorno);
        }
        throw new UsuarioNaoEncontradoException("Usuario nao encontrado.");
    }

    public CarrinhoDTO retornarCarrinhoPorId(Long id) {
        Optional<Carrinho> optionalCarrinho = carrinhoRepository.findById(id);
        if(!optionalCarrinho.isPresent()) {
            throw new CarrinhoNaoEncontradoException("Carrinho nao encontrado.");
        }
        return CarrinhoMapper.convertModelToDto(optionalCarrinho.get());
    }
}
