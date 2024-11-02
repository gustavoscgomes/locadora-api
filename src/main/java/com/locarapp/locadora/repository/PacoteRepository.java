package com.locarapp.locadora.repository;

import com.locarapp.locadora.entity.PacoteDeAluguel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacoteRepository extends JpaRepository<PacoteDeAluguel, Long> {

    Optional<PacoteDeAluguel> findByNome(String nome);
}
