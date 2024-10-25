package com.locarapp.locadora.service;

import com.locarapp.locadora.domain.Carro;
import com.locarapp.locadora.repository.CarroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {
    @Autowired
    private CarroRepository repository;

    public List<Carro> listarTodos() {
        return repository.findAll();
    }

    public List<Carro> listarDisponiveis() {
        return repository.findByDisponivel(true);
    }

    public Carro buscarCarroPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carro não encontrado com id: " + id));
    }

    public Carro salvar(Carro carro) {
        return repository.save(carro);
    }
}
