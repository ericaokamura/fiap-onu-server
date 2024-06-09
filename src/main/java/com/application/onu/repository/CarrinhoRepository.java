package com.application.onu.repository;

import com.application.onu.model.Carrinho;
import com.application.onu.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

    Carrinho save(Carrinho carrinho);

    Carrinho findByUsuario(Usuario usuario);

}
