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
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "pacote_id", referencedColumnName = "id")
    private Pacote pacote;
}
