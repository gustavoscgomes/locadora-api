package com.locarapp.locadora.controller;

import com.locarapp.locadora.domain.Carro;
import com.locarapp.locadora.domain.ContratoAluguel;
import com.locarapp.locadora.service.ContratoDeAluguelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.PanelUI;
import java.util.List;

@RestController
@RequestMapping("/contratos")
public class ContratoDeAluguelController {
    @Autowired
    private ContratoDeAluguelService contratoDeAluguelService;

    @GetMapping
    public ResponseEntity<List<ContratoAluguel>> listar() {
        List<ContratoAluguel> contratos = contratoDeAluguelService.listar();
        return ResponseEntity.ok(contratos);
    }

    @PostMapping("/{modelo}")
    public ResponseEntity<ContratoAluguel> salvar(@PathVariable String modelo) {
        ContratoAluguel novoContratoAluguel = contratoDeAluguelService.salvar(modelo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoContratoAluguel);
    }
}
