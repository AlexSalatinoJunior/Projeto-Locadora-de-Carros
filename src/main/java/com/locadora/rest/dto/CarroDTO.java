package com.locadora.rest.dto;

import lombok.Data;

@Data
public class CarroDTO {

    private String modelo;
    private String placa;
    private String categoria;
    private Float valorDiaria;

}
