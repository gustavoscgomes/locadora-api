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
@Table(name = "cartao_de_credito")
public class CartaoDeCredito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "numero", unique = true, nullable = false, length = 16)
    private Long numero;
    @Column(name = "mes", nullable = false, length = 2)
    private Integer mes;
    @Column(name = "ano", nullable = false, length = 2)
    private Integer ano;
    @Column(name = "cvv", nullable = false, length = 3)
    private Integer ccv;
    @OneToOne
    private Usuario usuario;

}
