package com.locadora.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "PEDIDOS")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ID_CARRO")
    private Carro carro;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ID_CLIENTE")
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
