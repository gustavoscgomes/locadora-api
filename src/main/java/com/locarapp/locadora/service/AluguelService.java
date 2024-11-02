package com.locarapp.locadora.service;

import com.locarapp.locadora.entity.Carro;
import com.locarapp.locadora.entity.Aluguel;
import com.locarapp.locadora.entity.Usuario;
import com.locarapp.locadora.exception.UnavailableCarException;
import com.locarapp.locadora.repository.CarroRepository;
import com.locarapp.locadora.repository.AluguelRepository;
import com.locarapp.locadora.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AluguelService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private AluguelRepository aluguelRepository;

    public List<Aluguel> listar() {
        return aluguelRepository.findAll();
    }

    public Aluguel salvar(String modelo) {
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

        var contrato = new Aluguel(carro, usuario);

        return aluguelRepository.save(contrato);
    }

}
