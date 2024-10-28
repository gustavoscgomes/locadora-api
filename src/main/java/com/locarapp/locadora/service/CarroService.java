package com.locarapp.locadora.service;

import com.locarapp.locadora.domain.Carro;
import com.locarapp.locadora.repository.CarroRepository;
import com.locarapp.locadora.repository.PacoteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {
    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private PacoteRepository pacoteRepository;


    public List<Carro> listarTodos() {
        return carroRepository.findAll();
    }

    public List<Carro> listarDisponiveis() {
        return carroRepository.findByDisponivel(true);
    }

    public Carro buscarCarroPorId(Long id) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carro não encontrado com id: " + id));

        return carro;
    }

    public Carro salvar(Carro carro) {
//        Carro carro = modelMapper.map(carroDTO, Carro.class);

        return carroRepository.save(carro);
    }

    public Carro editarCarro(Long id, Carro carro) {
        Carro carroEditado = carroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carro não encontrado com id: " + id));
        carroEditado.setModelo(carro.getModelo());
        carroEditado.setMarca(carro.getMarca());
        carroEditado.setAno(carro.getAno());
        carroEditado.setRenavam(carro.getRenavam());
        carroEditado.setDisponivel(carro.getDisponivel());

        return carroRepository.save(carroEditado);
    }
}
