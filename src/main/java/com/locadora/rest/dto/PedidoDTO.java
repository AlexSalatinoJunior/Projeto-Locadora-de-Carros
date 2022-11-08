package com.locadora.rest.dto;

public class PedidoDTO {
    private Integer usuario;
    private Integer carro;
    private Integer diasLocacao;
    private Integer valorTotal;

    public PedidoDTO() {
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public Integer getDiasLocacao() {
        return diasLocacao;
    }

    public void setDiasLocacao(Integer diasLocacao) {
        this.diasLocacao = diasLocacao;
    }

    public Integer getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Integer valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getCarro() {
        return carro;
    }

    public void setCarro(Integer carro) {
        this.carro = carro;
    }
}
