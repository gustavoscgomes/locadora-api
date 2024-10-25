package com.locarapp.locadora.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "carro")
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "modelo", nullable = false)
    private String modelo;
    @Column(name = "marca", nullable = false)
    private String marca;
    @Column(name = "ano", nullable = false, length = 4)
    private Integer ano;
    @Column(name = "renavam", nullable = false)
    private String renavam;
    @Column(name = "disponivel", nullable = false)
    private Boolean disponivel;
    @ManyToOne
    @JoinColumn(name = "pacote_de_aluguel_id", referencedColumnName = "id")
    private PacoteDeAluguel pacoteDeAluguel;
}
