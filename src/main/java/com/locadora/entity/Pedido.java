package com.locadora.entity;

import java.sql.Timestamp;

public class Pedido {
    private int id;
    private int idCarro;
    private int idCliente;
    private Timestamp dataLocacao;
    private int diasLocacao;
    private float valorTotal;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getIdCarro() {
        return idCarro;
    }
    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }

    public int getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Timestamp getDataLocacao() {
        return dataLocacao;
    }
    public void setDataLocacao(Timestamp dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public int getDiasLocacao() {
        return diasLocacao;
    }
    public void setDiasLocacao(int diasLocacao) {
        this.diasLocacao = diasLocacao;
    }

    public float getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }
}
