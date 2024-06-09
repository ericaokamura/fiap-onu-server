package com.application.onu.repository;

import com.application.onu.model.Carrinho;
import com.application.onu.model.Produto;
import com.application.onu.model.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByAnuncio(Anuncio anuncio);

    List<Produto> findByCarrinho(Carrinho usuario);

    Produto save(Produto produto);

    void deleteAllByCarrinho(Carrinho carrinho);
}
