package com.locarapp.locadora.controller;

import com.locarapp.locadora.domain.CartaoDeCredito;
import com.locarapp.locadora.domain.Usuario;
import com.locarapp.locadora.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PutMapping("/cartao")
    public Usuario adicionarCartao(@RequestBody CartaoDeCredito cartaoDeCredito) {
        return usuarioService.adicionarCartao(cartaoDeCredito);
    }
}
