package com.locadora.service;

import com.locadora.domain.entity.Pedido;
import com.locadora.domain.enums.StatusPedido;
import com.locadora.rest.dto.PedidoDTO;
import org.springframework.data.jpa.repository.Query;

import java.nio.channels.FileChannel;
import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
