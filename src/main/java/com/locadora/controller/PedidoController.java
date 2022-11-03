package com.locadora.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import com.locadora.entity.Carro;
import com.locadora.entity.Pedido;
import com.locadora.entity.Usuario;
import com.locadora.repository.Carros;
import com.locadora.repository.Pedidos;
import com.locadora.repository.Usuarios;

@Controller
public class PedidoController {

    private Pedidos pedidos;
    private Usuarios usuarios;
    private Carros carros;

    public PedidoController(Pedidos pedidos, Usuarios usuarios, Carros carros){
        this.pedidos = pedidos;
        this.usuarios = usuarios;
        this.carros = carros;
    }

    @GetMapping("/api/pedidos")
    @ResponseBody
    public ResponseEntity<Pedido> getPedidos(){
        List<Pedido> todosPedidos = pedidos.findAll();
        if(todosPedidos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/pedidos/id/{id}")
    @ResponseBody
    public ResponseEntity<Pedido> getPedidos(@PathVariable Integer id){
        Optional<Pedido> pedidoById = pedidos.findById(id);
        if(pedidoById.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/pedidos/usuario/{idUsuario}")
    @ResponseBody
    public ResponseEntity<Pedido> getPedidosPorUsuario(@PathVariable Integer idUsuario){
        Usuario usuario = usuarios.findById(idUsuario).get();
        List<Pedido> pedidosUsuario = pedidos.findByUsuario(usuario);
        if(pedidosUsuario.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/pedidos/carro/{idCarro}")
    @ResponseBody
    public ResponseEntity<Pedido> getPedidosPorCarro(@PathVariable Integer idCarro){
        Carro carro = carros.findById(idCarro).get();
        List<Pedido> pedidosCarro = pedidos.findByCarro(carro);
        if(pedidosCarro.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/pedidos/valor-total-maior/{valor}")
    @ResponseBody
    public ResponseEntity<Pedido> getPedidosPorValorTotalMaior(@PathVariable Double valor){
        List<Pedido> pedidosValorTotalMaiorQue = pedidos.findByValorTotalGreaterThan(valor);
        if(pedidosValorTotalMaiorQue.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/pedidos/valor-total-menor/{valor}")
    @ResponseBody
    public ResponseEntity<Pedido> getPedidosPorValorTotalMenor(@PathVariable Double valor){
        List<Pedido> pedidosValorTotalMenorQue = pedidos.findByValorTotalLessThan(valor);
        if(pedidosValorTotalMenorQue.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
