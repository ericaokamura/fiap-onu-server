package com.application.onu.repository;


import com.application.onu.model.Doacao;
import com.application.onu.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DoacaoRepository extends JpaRepository<Doacao, Long> {

    List<Doacao> findAllByUsuario(Usuario usuario);

    Doacao save(Doacao doacao);
}
