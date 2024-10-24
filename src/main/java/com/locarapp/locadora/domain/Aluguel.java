package com.locarapp.locadora.domain;

import com.locarapp.locadora.enums.StatusAluguel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "aluguel")
public class Aluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;
    @Column(name = "carro_id", nullable = false)
    private Long carroId;
    @Column(name = "marca", nullable = false)
    private String marca;
    @Column(name = "modelo", nullable = false)
    private String modelo;
    @Column(name = "data_inicio", nullable = false)
    private LocalDateTime dataInicio;
    @Column(name = "data_fim", nullable = false)
    private LocalDateTime dataFim;
    @Enumerated(EnumType.STRING)
    private StatusAluguel status;

}
