package com.locadora.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CARROS")
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String modelo;
    private String placa;
    private float valorDiaria;
    private boolean disponivel;

    @OneToMany(mappedBy = "carro")
    List<Pedido> carroPedidos = new ArrayList<Pedido>();

    public Carro(){}

    public Carro(String modelo, String placa, float valorDiaria){
        this.modelo = modelo;
        this.placa = placa;
        this.valorDiaria = valorDiaria;
        this.disponivel = true;
    }

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
        return valorDiaria;
    }
    public void setValorDiaria(float valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public List<Pedido> getCarroPedidos() {
        return carroPedidos;
    }
    public void addCarroPedidos(Pedido pedido) {
        carroPedidos.add(pedido);
    }

    public boolean isDisponivel() {
        return disponivel;
    }
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
