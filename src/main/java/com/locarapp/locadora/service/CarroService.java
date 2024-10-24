package com.locarapp.locadora.service;

import com.locarapp.locadora.domain.Carro;
import com.locarapp.locadora.repository.CarroRepository;
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
}
