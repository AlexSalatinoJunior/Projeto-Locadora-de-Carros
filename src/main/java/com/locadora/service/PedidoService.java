package com.locadora.service;

import com.locadora.domain.entity.Pedido;
import com.locadora.domain.enums.StatusPedido;
import com.locadora.rest.dto.PedidoDTO;

import java.util.List;
import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);

    List<Pedido> obterTodosPedidos();
}
