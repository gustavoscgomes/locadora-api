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
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "sobre_nome", nullable = false)
    private String sobreNome;
    @Column(name = "telefone", nullable = false)
    private Integer telefone;
    @Column(name = "email", nullable = false)
    private String email;
//    @JsonIgnore
//    @OneToOne(mappedBy = "cliente", orphanRemoval = true)
//    private CartaoDeCredito cartaoDeCredito;
}
