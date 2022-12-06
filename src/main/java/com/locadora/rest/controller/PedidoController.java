package com.locadora.rest.controller;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

import com.locadora.domain.enums.StatusPedido;
import com.locadora.domain.repository.Clientes;
import com.locadora.domain.repository.Pedidos;
import com.locadora.domain.repository.Usuarios;
import com.locadora.rest.dto.AtualizacaoStatusPedidoDTO;
import com.locadora.rest.dto.InformacoesPedidoDTO;
import com.locadora.rest.dto.MultaDTO;
import com.locadora.rest.dto.PedidoDTO;
import com.locadora.service.PedidoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.locadora.domain.entity.Cliente;
import com.locadora.domain.entity.Pedido;
import com.locadora.domain.entity.Usuario;

import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PedidoController {
    private PedidoService pedidoService;
    private Pedidos pedidosRepo;
    private Clientes clientesRepo;
    private Usuarios usuariosRepo;

    public PedidoController(PedidoService pedidoService, Pedidos pedidosRepo, Clientes clientesRepo, Usuarios usuariosRepo){
        this.pedidoService = pedidoService;
        this.pedidosRepo = pedidosRepo;
        this.clientesRepo = clientesRepo;
        this.usuariosRepo = usuariosRepo;
    }
    @PostMapping("/id")
    @ResponseStatus(CREATED)
    public ResponseEntity saveNovoPedido(@RequestBody PedidoDTO dto){
        pedidoService.salvar(dto);
        System.out.println(dto);
        return ResponseEntity.noContent().build();
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

    @GetMapping
    public List<InformacoesPedidoDTO> getAll(){
        List<InformacoesPedidoDTO> pedidos = new ArrayList<>();
        pedidoService
                .obterTodosPedidos()
                .forEach(pedido -> {
                   InformacoesPedidoDTO novoPedido = converter(pedido);
                   pedidos.add(novoPedido);
                });
        return pedidos;
    }

    @PatchMapping("/id/{id}")
    public MultaDTO updateStatus(@PathVariable Integer id, @RequestBody AtualizacaoStatusPedidoDTO dto){
        String novoStatus = dto.getNovoStatus();
        pedidoService.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
        MultaDTO multaDTO = new MultaDTO();
        multaDTO.setValorMulta(pedidoService.calculaMulta(id, dto.getDiasUsados()));
        return multaDTO;
    }

    @GetMapping("/login/{login}")
    public List<Pedido> getPedidosUsuario(@PathVariable String login){
        List<Pedido> pedidos = new ArrayList<>();
        Usuario usuario = usuariosRepo.findByLogin(login);
        pedidos = pedidosRepo.findByUsuario(usuario);
        return pedidos;
    }

    @GetMapping("/userid/{id}")
    public List<Pedido> getPedidosIdUsuario(@PathVariable Integer id){
        List<Pedido> pedidos = new ArrayList<>();
        Usuario usuario = usuariosRepo.findById(id).get();
        pedidos = pedidosRepo.findByUsuario(usuario);
        return pedidos;
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
