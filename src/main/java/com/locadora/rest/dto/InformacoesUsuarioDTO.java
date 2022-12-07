package com.locadora.rest.dto;

import com.locadora.domain.entity.Address;
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
    private Address address;
}
