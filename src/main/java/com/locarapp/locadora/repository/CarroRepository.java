package com.locarapp.locadora.repository;

import com.locarapp.locadora.domain.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    List<Carro> findByDisponivel(Boolean disponivel);

    Optional<Carro> findByModelo(String modelo);
}
