package com.application.onu.repository;

import com.application.onu.model.Doacao;
import com.application.onu.model.Recebimento;
import com.application.onu.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecebimentoRepository extends JpaRepository<Recebimento, Long> {

    List<Recebimento> findAllByUsuario(Usuario usuario);

    Recebimento save(Recebimento recebimento);
}

