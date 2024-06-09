package com.application.onu.service;

import com.application.onu.exception.AnuncioNaoEncontradoException;
import com.application.onu.exception.CarrinhoNaoEncontradoException;
import com.application.onu.exception.UsuarioNaoEncontradoException;
import com.application.onu.mapper.AnuncioMapper;
import com.application.onu.mapper.ProdutoMapper;
import com.application.onu.model.Anuncio;
import com.application.onu.model.Carrinho;
import com.application.onu.model.Produto;
import com.application.onu.model.Usuario;
import com.application.onu.model.dto.ProdutoDTO;
import com.application.onu.repository.AnuncioRepository;
import com.application.onu.repository.CarrinhoRepository;
import com.application.onu.repository.ProdutoRepository;
import com.application.onu.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public ProdutoDTO adicionarProdutoEmCarrinhoDeUsuario(ProdutoDTO produtoDTO){
        Produto produto = new Produto();
        produto.setDataHoraProdutoAdicionado(produtoDTO.getDataHoraProdutoAdicionado());
        Optional<Anuncio> anuncioOptional = anuncioRepository.findById(produtoDTO.getAnuncioId());
        if(anuncioOptional.isPresent()) {
            Anuncio anuncio = anuncioOptional.get();
            produto.setAnuncio(anuncio);
        } else  {
            throw new AnuncioNaoEncontradoException("Anuncio nao encontrado.");
        }
        Optional<Carrinho> carrinhoOptional = carrinhoRepository.findById(produtoDTO.getCarrinhoId());
        if(carrinhoOptional.isPresent()) {
            Carrinho carrinho = carrinhoOptional.get();
            produto.setCarrinho(carrinho);
        } else {
            throw new CarrinhoNaoEncontradoException("Carrinho nao encontrado.");
        }
        Produto retorno = produtoRepository.save(produto);
        return ProdutoMapper.convertModelToDto(retorno);
    }

    public List<ProdutoDTO> retornarProdutosEmCarrinhoDeUsuario(String carrinhoId) {
        Optional<Carrinho> optionalCarrinho = carrinhoRepository.findById(Long.parseLong(carrinhoId));
        if(!optionalCarrinho.isPresent()) {
            throw new CarrinhoNaoEncontradoException("Carrinho nao encontrado.");
        }
        List<Produto> produtos = produtoRepository.findByCarrinho(optionalCarrinho.get());
        return ProdutoMapper.convertListModelToListDto(produtos);
    }

    public void esvaziarCarrinhoDeUsuario(String carrinhoId) {
        Optional<Carrinho> optionalCarrinho = carrinhoRepository.findById(Long.parseLong(carrinhoId));
        if(!optionalCarrinho.isPresent()) {
            throw new CarrinhoNaoEncontradoException("Carrinho nao encontrado.");
        }
        List<Produto> produtos = produtoRepository.findByCarrinho(optionalCarrinho.get());
        produtos.forEach(p -> {
            produtoRepository.deleteById(p.getId());
        });
    }
}
