package com.locadora.domain.entity;

import java.util.Set;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.locadora.domain.enums.Categoria;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "CARROS")
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private String modelo;
    private String placa;
    private Categoria categoria;
    private float valorDiaria;
    private boolean disponivel = true;

    public Carro(String modelo, String placa, Categoria categoria, float valorDiaria){
        this.modelo = modelo;
        this.placa = placa;
        this.categoria = categoria;
        this.valorDiaria = valorDiaria;
    }
}
