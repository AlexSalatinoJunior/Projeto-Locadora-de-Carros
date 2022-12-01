package com.locadora.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InformacoesUsuarioDTO {
    private Integer id;
    private String nomeUsuario;
    private String cpf;
    private String cnh;
    private String email;
    private Integer idCarro;
    private String modeloCarro;
    private boolean administrador;
}
