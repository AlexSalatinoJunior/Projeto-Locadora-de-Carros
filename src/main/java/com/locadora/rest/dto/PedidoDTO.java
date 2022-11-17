package com.locadora.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class PedidoDTO {
    private Integer usuario;
    private Integer carro;
    private Integer diasLocacao;
    private Integer valorTotal;

    public PedidoDTO() {
    }

}
