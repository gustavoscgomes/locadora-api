package com.locarapp.locadora.repository;

import com.locarapp.locadora.domain.CartaoDeCredito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoDeCreditoRepository extends JpaRepository<CartaoDeCredito, Long> {
}
