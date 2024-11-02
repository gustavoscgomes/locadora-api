package com.locarapp.locadora.mapper;

import com.locarapp.locadora.dto.PacoteDeAluguelDTO;
import com.locarapp.locadora.entity.PacoteDeAluguel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PacoteDeAluguelMapper {

    PacoteDeAluguelDTO toPacoteDeAluguelDTO(PacoteDeAluguel pacoteDeAluguel);

    PacoteDeAluguel toPacoteDeAluguel(PacoteDeAluguelDTO pacoteDeAluguelDTO);

}
