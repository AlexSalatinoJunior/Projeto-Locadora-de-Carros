package com.locadora.rest.dto;

import com.locadora.domain.entity.Address;
import lombok.Data;

@Data
public class ClienteDTO {

    private String nome;
    private String cpf;
    private String cnh;
    private String login;
    private String senha;
    private Address address;

    public ClienteDTO(){}
}
