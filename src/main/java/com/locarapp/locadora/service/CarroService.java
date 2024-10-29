package com.locarapp.locadora.service;

import com.locarapp.locadora.domain.Carro;
import com.locarapp.locadora.domain.PacoteDeAluguel;
import com.locarapp.locadora.exception.ExistingEntityException;
import com.locarapp.locadora.repository.CarroRepository;
import com.locarapp.locadora.repository.PacoteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CarroService {
    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private PacoteRepository pacoteRepository;


    public List<Carro> listarCarros() {
        return carroRepository.findAll();
    }

    public List<Carro> listarCarrosDisponiveis() {
        return carroRepository.findByDisponivel(true);
    }

    public Carro buscarCarroPorId(Long id) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carro não encontrado com id: " + id));
        return carro;
    }

    public Carro salvarNovoCarro(Carro carro) {
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

    public void deleteCarro(Long id) {
        if(!carroRepository.existsById(id)) {
            throw new EntityNotFoundException("Carro não encontrado com id: " + id);
        }
        carroRepository.deleteById(id);
    }

    public List<PacoteDeAluguel> listarPacotes() {
        return pacoteRepository.findAll();
    }

    public Carro adicionarPacote(Long id, String pacoteNome) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carro não encontrado com id: " + id));
        PacoteDeAluguel pacoteDeAluguel = pacoteRepository.findByNome(pacoteNome)
                .orElseThrow(() -> new EntityNotFoundException("Pacote não encontrado"));
        carro.setPacoteDeAluguel(pacoteDeAluguel);
        return carroRepository.save(carro);
    }

    public PacoteDeAluguel salvarNovoPacote(PacoteDeAluguel pacoteDeAluguel) {
        if (pacoteRepository.findByNome(pacoteDeAluguel.getNome()).isPresent()) {
            throw new ExistingEntityException("Este Pacote Ja existe");
        }
        return pacoteRepository.save(pacoteDeAluguel);
    }

    public void deletePacote(Long id) {
        if(!pacoteRepository.existsById(id)) {
            throw new EntityNotFoundException("Pacote não encontrado");
        }
        PacoteDeAluguel pacoteDeAluguel = pacoteRepository.getById(id);
        Collection<Carro> carros = pacoteDeAluguel.getCarros();
        for (Carro carro : carros) {
            carro.setPacoteDeAluguel(null);
        }
        pacoteRepository.delete(pacoteDeAluguel);
    }

}
