package com.locadora.rest.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private String nome;
    private String cpf;
    private String cnh;
    private String login;
    private String senha;
    private Boolean admin;
}
