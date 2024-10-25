package com.locarapp.locadora.repository;

import com.locarapp.locadora.domain.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    List<Carro> findByDisponivel(Boolean disponivel);
}
