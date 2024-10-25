package com.locarapp.locadora.controller;

import com.locarapp.locadora.domain.Carro;
import com.locarapp.locadora.service.CarroService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarController {
    @Autowired
    private CarroService service;

    @GetMapping
    public ResponseEntity<List<Carro>> listarTodos() {
        List<Carro> carros = service.listarTodos();
        return ResponseEntity.ok(carros);
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<Carro>> listarDisponiveis() {
        List<Carro> carrosDisponiveis = service.listarDisponiveis();
        return ResponseEntity.ok(carrosDisponiveis);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> buscarCarroPorId(@PathVariable Long id) {
        try {
            Carro carro = service.buscarCarroPorId(id);
            return ResponseEntity.ok(carro);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Carro> salvar(@RequestBody Carro carro) {
        Carro novoCarro = service.salvar(carro);
        return ResponseEntity.ok(novoCarro);
    }


}
