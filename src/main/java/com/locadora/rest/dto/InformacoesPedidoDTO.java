package com.locadora.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InformacoesPedidoDTO {
    private Integer id;
    private String nomeCliente;
    private String cnh;
    private String modeloCarro;
    private String placa;
    private Integer diasLocacao;
    private double valorTotal;
}
