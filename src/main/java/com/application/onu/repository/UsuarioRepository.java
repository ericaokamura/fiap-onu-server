package com.application.onu.repository;

import com.application.onu.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findById(Long usuarioId);

    Usuario save(Usuario usuario);

    Optional<Usuario> findByNomeUsuario(String nomeUsuario);

}
