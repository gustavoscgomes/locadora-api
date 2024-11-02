package com.locarapp.locadora.mapper;

import com.locarapp.locadora.dto.CarroDTO;
import com.locarapp.locadora.entity.Carro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarroMapper {

//    @Mapping(target = "id", ignore = true) // Ignora o ID
//    @Mapping(target = "pacoteDeAluguel", ignore = true)
    CarroDTO toCarroDTO(Carro carro);

//    @Mapping(target = "id", ignore = true) // Ignora o ID durante a criação
//    @Mapping(target = "pacoteDeAluguel", ignore = true) // Ignora o pacoteDeAluguel se for necessário
    Carro toCarro(CarroDTO carroDTO);
}
