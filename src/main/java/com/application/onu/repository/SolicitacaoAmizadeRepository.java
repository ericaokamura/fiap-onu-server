package com.application.onu.repository;

import com.application.onu.model.SolicitacaoAmizade;
import com.application.onu.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SolicitacaoAmizadeRepository extends JpaRepository<SolicitacaoAmizade, Long> {

    SolicitacaoAmizade save(SolicitacaoAmizade solicitacaoAmizade);

    List<SolicitacaoAmizade> findAllByRecebe(Usuario recebe);

    Optional<SolicitacaoAmizade> findAllByRecebeAndSolicitante(Usuario recebe, Usuario solicitante);

}

