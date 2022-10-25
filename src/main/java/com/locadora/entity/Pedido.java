package com.locadora.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PEDIDOS")
public class Pedido {
    @Id
    private int id;
    private int id_carro;
    private int id_cliente;
    private int dias_locacao;
    private float valor_total;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getIdCarro() {
        return id_carro;
    }
    public void setIdCarro(int id_carro) {
        this.id_carro = id_carro;
    }

    public int getIdCliente() {
        return id_cliente;
    }
    public void setIdCliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getDiasLocacao() {
        return dias_locacao;
    }
    public void setDiasLocacao(int dias_locacao) {
        this.dias_locacao = dias_locacao;
    }

    public float getValorTotal() {
        return valor_total;
    }
    public void setValorTotal(float valor_total) {
        this.valor_total = valor_total;
    }
}
