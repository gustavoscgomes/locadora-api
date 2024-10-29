package com.locarapp.locadora.service;

import com.locarapp.locadora.domain.Carro;
import com.locarapp.locadora.domain.ContratoAluguel;
import com.locarapp.locadora.domain.Usuario;
import com.locarapp.locadora.exception.UnavailableCarException;
import com.locarapp.locadora.repository.CarroRepository;
import com.locarapp.locadora.repository.ContratoAluguelRepository;
import com.locarapp.locadora.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratoDeAluguelService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private ContratoAluguelRepository contratoAluguelRepository;

    public List<ContratoAluguel> listar() {
        return contratoAluguelRepository.findAll();
    }

    public ContratoAluguel salvar(String modelo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Recupera o usuario pelo nome de usuario
        Usuario usuario = usuarioRepository.findUsuarioByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado"));

        // Recupera o carro pelo modelo
        Carro carro = carroRepository.findByModelo(modelo)
                .orElseThrow(() -> new EntityNotFoundException("Carro não encontrado para o modelo: " + modelo));

        if (!carro.getDisponivel()) {
            throw new UnavailableCarException("O carro " + modelo + " não está disponível para aluguel");
        }

        var contrato = new ContratoAluguel(carro, usuario);

        return contratoAluguelRepository.save(contrato);
    }

}
