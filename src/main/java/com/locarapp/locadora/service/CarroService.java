package com.locarapp.locadora.service;

import com.locarapp.locadora.dto.CarroDTO;
import com.locarapp.locadora.dto.PacoteDeAluguelDTO;
import com.locarapp.locadora.entity.Carro;
import com.locarapp.locadora.entity.PacoteDeAluguel;
import com.locarapp.locadora.exception.ExistingEntityException;
import com.locarapp.locadora.mapper.CarroMapper;
import com.locarapp.locadora.mapper.PacoteDeAluguelMapper;
import com.locarapp.locadora.repository.CarroRepository;
import com.locarapp.locadora.repository.PacoteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarroService {
    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private PacoteRepository pacoteRepository;

    @Autowired
    private CarroMapper carroMapper;

    @Autowired
    PacoteDeAluguelMapper pacoteDeAluguelMapper;


//    public List<Carro> listarCarros() {
//        return carroRepository.findAll();
//    }
//
//    public List<Carro> listarCarrosDisponiveis() {
//        return carroRepository.findByDisponivel(true);
//    }

    public List<CarroDTO> listarCarros() {
        return carroRepository.findAll().stream()
                .map(carro -> carroMapper.toCarroDTO(carro))
                .collect(Collectors.toList());
    }

    public List<CarroDTO> listarCarrosDisponiveis() {
        return carroRepository.findByDisponivel(true).stream()
                .map(carro -> carroMapper.toCarroDTO(carro))
                .collect(Collectors.toList());
    }

    public CarroDTO buscarCarroPorId(Long id) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carro não encontrado com id: " + id));
        return carroMapper.toCarroDTO(carro);
    }

//    public Carro buscarCarroPorId(Long id) {
//        Carro carro = carroRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Carro não encontrado com id: " + id));
//        return carro;
//    }

//    public Carro salvarNovoCarro(Carro carro) {
//        return carroRepository.save(carro);
//    }

    public CarroDTO salvarNovoCarro(CarroDTO carroDTO) {
        Carro carro = carroMapper.toCarro(carroDTO);
        carro = carroRepository.save(carro);
        return carroMapper.toCarroDTO(carro);
    }

//    public Carro editarCarro(Long id, Carro carro) {
//        Carro carroEditado = carroRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Carro não encontrado com id: " + id));
//        carroEditado.setModelo(carro.getModelo());
//        carroEditado.setMarca(carro.getMarca());
//        carroEditado.setAno(carro.getAno());
//        carroEditado.setRenavam(carro.getRenavam());
//        carroEditado.setDisponivel(carro.getDisponivel());
//
//        return carroRepository.save(carroEditado);
//    }

    public CarroDTO editarCarro(Long id, CarroDTO carroDTO) {
        Carro carroEditado = carroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carro não encontrado com id: " + id));

        carroEditado.setModelo(carroDTO.modelo());
        carroEditado.setMarca(carroDTO.marca());
        carroEditado.setAno(carroDTO.ano());
        carroEditado.setRenavam(carroDTO.renavam());
        carroEditado.setDisponivel(carroDTO.disponivel());

        carroEditado = carroRepository.save(carroEditado);
        return carroMapper.toCarroDTO(carroEditado);
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


    public CarroDTO adicionarPacote(Long id, String pacoteNome) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carro não encontrado com id: " + id));
        PacoteDeAluguel pacoteDeAluguel = pacoteRepository.findByNome(pacoteNome)
                .orElseThrow(() -> new EntityNotFoundException("Pacote não encontrado"));

        carro.setPacoteDeAluguel(pacoteDeAluguel);
        carro = carroRepository.save(carro);
        return carroMapper.toCarroDTO(carro);
    }

    public PacoteDeAluguelDTO salvarNovoPacote(PacoteDeAluguelDTO pacoteDeAluguelDTO) {
        PacoteDeAluguel pacoteDeAluguel = pacoteDeAluguelMapper.toPacoteDeAluguel(pacoteDeAluguelDTO);
        if (pacoteRepository.findByNome(pacoteDeAluguel.getNome()).isPresent()) {
            throw new ExistingEntityException("Este Pacote Ja existe");
        }
        pacoteDeAluguel = pacoteRepository.save(pacoteDeAluguel);
        return pacoteDeAluguelMapper.toPacoteDeAluguelDTO(pacoteDeAluguel);
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
