package com.locadora.rest.controller;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

import com.locadora.domain.enums.StatusPedido;
import com.locadora.rest.dto.AtualizacaoStatusPedidoDTO;
import com.locadora.rest.dto.InformacoesPedidoDTO;
import com.locadora.rest.dto.PedidoDTO;
import com.locadora.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.locadora.domain.entity.Carro;
import com.locadora.domain.entity.Pedido;
import com.locadora.domain.entity.Usuario;
import com.locadora.domain.repository.Carros;
import com.locadora.domain.repository.Pedidos;
import com.locadora.domain.repository.Usuarios;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    private Pedidos pedidos;
    private Usuarios usuarios;
    private Carros carros;
    private PedidoService pedidoService;

    public PedidoController(Pedidos pedidos, Usuarios usuarios, Carros carros, PedidoService pedidoService){
        this.pedidos = pedidos;
        this.usuarios = usuarios;
        this.carros = carros;
        this.pedidoService = pedidoService;
    }
    @PostMapping("/id")
    @ResponseStatus(CREATED)
    public Integer saveNovoPedido(@RequestBody PedidoDTO dto){
        Pedido pedido = pedidoService.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("/id/{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id){
        return pedidoService
                .obterPedidoCompleto(id)
                .map(p -> converter(p))
                .orElseThrow(
                        () -> new ResponseStatusException(NOT_FOUND, "Pedido não encontrado")
                );
    }

    @PatchMapping("/id/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStatus(@PathVariable Integer id, @RequestBody AtualizacaoStatusPedidoDTO dto){

        String novoStatus = dto.getNovoStatus();
        pedidoService.atualizaStatus(id, StatusPedido.valueOf(novoStatus));

    }

    private InformacoesPedidoDTO converter(Pedido pedido){
        return InformacoesPedidoDTO.builder()
                .id(pedido.getId())
                .diasLocacao(pedido.getDiasLocacao())
                .modeloCarro(pedido.getCarro().getModelo())
                .placa(pedido.getCarro().getPlaca())
                .diasLocacao(pedido.getDiasLocacao())
                .valorTotal(pedido.getValorTotal())
                .nomeCliente(pedido.getUsuario().getNome())
                .cnh(pedido.getUsuario().getCnh())
                .valorDiaria(pedido.getCarro().getValorDiaria())
                .status(pedido.getStatus().name())
                .build();
    }
}
