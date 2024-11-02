package com.locarapp.locadora.controller;

import com.locarapp.locadora.entity.Aluguel;
import com.locarapp.locadora.service.AluguelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alugueis")
public class AluguelController {
    @Autowired
    private AluguelService aluguelService;

    @GetMapping
    public ResponseEntity<List<Aluguel>> listar() {
        List<Aluguel> contratos = aluguelService.listar();
        return ResponseEntity.ok(contratos);
    }

    @PostMapping("/{modelo}")
    public ResponseEntity<Aluguel> salvar(@PathVariable String modelo) {
        Aluguel novoAluguel = aluguelService.salvar(modelo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAluguel);
    }
}
