package com.locarapp.locadora.dto;

import com.locarapp.locadora.entity.PacoteDeAluguel;

public record CarroDTO(
        Long id,
        String modelo,
        String marca,
        Integer ano,
        String renavam,
        Boolean disponivel,
        PacoteDeAluguel pacoteDeAluguel) {}
