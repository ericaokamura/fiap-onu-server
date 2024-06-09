package com.application.onu.repository;

import com.application.onu.model.Compra;
import com.application.onu.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

    List<Compra> findAllByUsuario(Usuario usuario);

    Compra save(Compra venda);
}
