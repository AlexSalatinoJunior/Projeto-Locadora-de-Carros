package com.locadora.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PEDIDOS")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(name = "carro")
    private Carro carro;

    @ManyToOne
    @JoinColumn(name = "usuario")
    private Usuario usuario;

    private int diasLocacao;

    @Column(name = "valor_total", scale = 2, precision = 20)
    private double valorTotal;

    public Pedido(){}

    public Pedido(Carro carro, Usuario usuario, int diasLocacao){
        this.carro = carro;
        this.usuario = usuario;
        this.diasLocacao = diasLocacao;
        this.valorTotal = diasLocacao * carro.getValorDiaria();
        carro.setDisponivel(false);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Carro getCarro() {
        return carro;
    }
    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getDiasLocacao() {
        return diasLocacao;
    }
    public void setDiasLocacao(int diasLocacao) {
        this.diasLocacao = diasLocacao;
    }

    public double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(double d) {
        this.valorTotal = d;
    }
}
