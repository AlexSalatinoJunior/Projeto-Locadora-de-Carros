package com.locadora.rest.dto;

import lombok.Data;

@Data
public class PedidoDTO {
    private Integer usuario;
    private Integer carro;
    private Integer diasLocacao;
    private Integer valorTotal;

    public PedidoDTO() {
    }

}
