package com.application.onu.repository;


import com.application.onu.model.Usuario;
import com.application.onu.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

    List<Venda> findAllByUsuario(Usuario usuario);

    Venda save(Venda venda);
}

