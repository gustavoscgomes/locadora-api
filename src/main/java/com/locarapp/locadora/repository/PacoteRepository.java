package com.locarapp.locadora.repository;

import com.locarapp.locadora.domain.PacoteDeAluguel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacoteRepository extends JpaRepository<PacoteDeAluguel, Long> {
}
