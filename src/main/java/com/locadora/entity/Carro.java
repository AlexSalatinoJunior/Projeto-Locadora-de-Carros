package com.locadora.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CARROS")
public class Carro {
    @Id
    private int id;
    private String modelo;
    private String placa;
    private float valor_diaria;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public float getValorDiaria() {
        return valor_diaria;
    }
    public void setValorDiaria(float valor_diaria) {
        this.valor_diaria = valor_diaria;
    }
}
