package com.locadora.domain.entity;

import com.locadora.domain.enums.StatusPedido;
import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "PEDIDOS")
@Data
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

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    public Pedido(){}

    public Pedido(Carro carro, Usuario usuario, int diasLocacao){
        this.carro = carro;
        this.usuario = usuario;
        this.diasLocacao = diasLocacao;
        this.valorTotal = diasLocacao * carro.getValorDiaria();
        this.status = StatusPedido.REALIZADO;
        carro.setDisponivel(false);
    }

}
