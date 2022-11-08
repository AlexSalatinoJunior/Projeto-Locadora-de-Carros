package com.locadora.rest.controller;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

import com.locadora.rest.dto.PedidoDTO;
import com.locadora.service.PedidoService;
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

    @GetMapping
    public List<Pedido> getPedidos(){
        if(pedidos.findAll().isEmpty()){
            throw new ResponseStatusException(NOT_FOUND, "Pedidos não encontrados");
        }
        return pedidos.findAll();
    }

    @GetMapping("/id/{id}")
    public Pedido getPedido(@PathVariable Integer id){
        return pedidos.findById(id).orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND, "Pedido não encontrado")
        );
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<Pedido> getPedidosPorUsuario(@PathVariable Integer idUsuario){
        Usuario usuario = usuarios.findById(idUsuario).get();
        if(pedidos.findByUsuario(usuario).isEmpty()){
            throw new ResponseStatusException(NOT_FOUND, "Pedidos não encontrados para usuário");
        }
        return pedidos.findByUsuario(usuario);
    }

    @GetMapping("/carro/{idCarro}")
    public List<Pedido> getPedidosPorCarro(@PathVariable Integer idCarro){
        Carro carro = carros.findById(idCarro).get();
        if(pedidos.findByCarro(carro).isEmpty()){
            throw new ResponseStatusException(NOT_FOUND, "Sem pedidos anteriores para o carro");
        }
        return pedidos.findByCarro(carro);
    }

    @GetMapping("/valor-total-maior/{valor}")
    public List<Pedido> getPedidosPorValorTotalMaior(@PathVariable Double valor){
        if(pedidos.findByValorTotalGreaterThan(valor).isEmpty()){
            throw new ResponseStatusException(NOT_FOUND, "Sem pedidos de maior valor");
        }

        return pedidos.findByValorTotalGreaterThan(valor);
    }

    @GetMapping("/valor-total-menor/{valor}")
    public List<Pedido> getPedidosPorValorTotalMenor(@PathVariable Double valor){
        if(pedidos.findByValorTotalLessThan(valor).isEmpty()){
            throw new ResponseStatusException(NOT_FOUND, "Sem pedidos de menor valor");
        }

        return pedidos.findByValorTotalLessThan(valor);
    }


    @PostMapping("/id")
    @ResponseStatus(CREATED)
    public Integer saveNovoPedido(@RequestBody PedidoDTO dto){
        Pedido pedido = pedidoService.salvar(dto);
        return pedido.getId();
    }
}
