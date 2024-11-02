package com.locarapp.locadora.repository;

import com.locarapp.locadora.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByUsername(String username);
    Optional<Usuario> findUsuarioByUsername(String username);
}
