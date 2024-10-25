package com.locarapp.locadora.repository;

import ch.qos.logback.core.net.server.Client;
import com.locarapp.locadora.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
