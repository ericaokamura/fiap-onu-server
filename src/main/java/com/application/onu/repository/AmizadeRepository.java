package com.application.onu.repository;

import com.application.onu.model.Amizade;
import com.application.onu.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AmizadeRepository extends JpaRepository<Amizade, Long> {

    Amizade save(Amizade amizade);

    Optional<Amizade> findBySeguidorAndSeguido(Usuario seguidor, Usuario seguido);

}

