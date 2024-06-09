package com.application.onu.repository;

import com.application.onu.model.Anuncio;
import com.application.onu.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {

    List<Anuncio> findAll();
    List<Anuncio> findAllByUsuario(Usuario usuario);

    List<Anuncio> findByDescricaoContaining(@Param("descricao") String descricao);

}
