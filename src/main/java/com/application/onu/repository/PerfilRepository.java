package com.application.onu.repository;

import com.application.onu.model.Perfil;
import com.application.onu.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    Perfil save(Perfil perfil);

    Optional<Perfil> findByUsuario(Usuario usuario);

}
