package com.locadora.service;

import com.locadora.domain.entity.Pedido;
import com.locadora.rest.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);
}
