package com.application.onu.repository;

import com.application.onu.model.Configuracao;
import com.application.onu.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.ObjectInputFilter;

@Repository
public interface ConfiguracaoRepository extends JpaRepository<Configuracao, Long> {

    Configuracao findByUsuario(Usuario usuario);

    Configuracao save(Configuracao configuracao);

}
