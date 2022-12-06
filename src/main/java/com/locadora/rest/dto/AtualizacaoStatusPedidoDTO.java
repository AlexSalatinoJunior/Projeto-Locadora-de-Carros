package com.locadora.rest.dto;

import lombok.Data;
@Data
public class AtualizacaoStatusPedidoDTO {
    private Integer diasUsados;
    private String novoStatus;
}
