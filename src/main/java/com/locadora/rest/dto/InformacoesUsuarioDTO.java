package com.locadora.rest.dto;

import com.locadora.domain.entity.Carro;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InformacoesUsuarioDTO {
    private Integer id;
    private String nomeUsuario;
    private String cnh;
    private Integer idCarro;
    private String modeloCarro;
    private boolean administrador;
}
