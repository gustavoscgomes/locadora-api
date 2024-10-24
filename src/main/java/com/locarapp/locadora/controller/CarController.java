package com.locarapp.locadora.controller;

import com.locarapp.locadora.domain.Carro;
import com.locarapp.locadora.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarController {
    @Autowired
    private CarroService service;

    @GetMapping("/carros")
    public List<Carro> listarTodos() {
        return service.listarTodos();
    }


}
