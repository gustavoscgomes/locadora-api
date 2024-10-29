package com.locarapp.locadora.controller;

import com.locarapp.locadora.domain.Carro;
import com.locarapp.locadora.domain.PacoteDeAluguel;
import com.locarapp.locadora.service.CarroService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {
    @Autowired
    private CarroService service;

    @Operation(summary = "Recupera todos os carros",
            description = "Retorna uma lista de todos os carros disponíveis ou não para locação.")
    @GetMapping
    public ResponseEntity<List<Carro>> listarTodos() {
        List<Carro> carros = service.listarCarros();
        return ResponseEntity.ok(carros);
    }

    @Operation(summary = "Recupera todos os carros disponíveis",
            description = "Retorna uma lista de todos os carros disponíveis para locação.")
    @GetMapping("/disponiveis")
    public ResponseEntity<List<Carro>> listarDisponiveis() {
        List<Carro> carrosDisponiveis = service.listarCarrosDisponiveis();
        return ResponseEntity.ok(carrosDisponiveis);
    }

    @Operation(summary = "Recupera um carro por ID",
            description = "Retorna as informações de um carro específico, fornecendo seu ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Carro> buscarCarroPorId(@PathVariable Long id) {
        try {
            Carro carro = service.buscarCarroPorId(id);
            return ResponseEntity.ok(carro);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Cria um novo carro",
            description = "Adiciona um novo carro ao sistema, permitindo que ele seja alugado.")
    @PostMapping
    public ResponseEntity<Carro> createCarro(@RequestBody Carro carro) {
        Carro novoCarro = service.salvarNovoCarro(carro);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCarro);
    }

    @Operation(summary = "Atualiza informações de um carro",
            description = "Atualiza os detalhes de um carro específico usando o ID fornecido.")
    @PutMapping("/{id}")
    public ResponseEntity<Carro> updateCarro(@PathVariable Long id,@RequestBody Carro carro) {
        Carro carroEditado = service.editarCarro(id, carro);
        return ResponseEntity.ok(carroEditado);
    }

    @Operation(summary = "Exclui um carro",
            description = "Remove um carro do sistema usando o ID fornecido.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarro(@PathVariable Long id) {
        service.deleteCarro(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Recupera todos os pacotes de aluguel",
            description = "Retorna uma lista de todos os pacotes de locação.")
    @GetMapping("/pacotes")
    public ResponseEntity<List<PacoteDeAluguel>> listarPacotes() {
        List<PacoteDeAluguel> pacotes = service.listarPacotes();
        return ResponseEntity.ok(pacotes);
    }

    @Operation(summary = "Cria um novo pacote de locação",
            description = "Adiciona um novo pacote de locação ao sistema")
    @PostMapping("/pacotes")
    public ResponseEntity<PacoteDeAluguel> salvarNovoPacote(@RequestBody PacoteDeAluguel pacoteDeAluguel) {
        PacoteDeAluguel novoPacote = service.salvarNovoPacote(pacoteDeAluguel);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPacote);
    }

    @Operation(summary = "Adiciona um pacote de locação a um carro",
            description = "Associa um pacote de locação ao carro especificado pelo ID, " +
                    "permitindo que sejam aplicadas condições específicas de locação.")
    @PutMapping("/{id}/pacotes")
    public ResponseEntity<Carro> adicionarPacote(@PathVariable Long id, @RequestParam String pacoteNome) {
        Carro carro = service.adicionarPacote(id, pacoteNome);
        return ResponseEntity.ok(carro);
    }

    @Operation(summary = "Exclui um pacote",
            description = "Remove um pacote do sistema usando o ID fornecido.")
    @DeleteMapping("/pacotes/{id}")
    public ResponseEntity<Void> deletePacote(@PathVariable Long id) {
        service.deletePacote(id);
        return ResponseEntity.noContent().build();
    }


}
