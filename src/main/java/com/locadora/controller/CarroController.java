package com.locadora.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.locadora.entity.Carro;
import com.locadora.repository.Carros;

import javax.websocket.ClientEndpoint;

@Controller
public class CarroController {

    private Carros carros;

    public CarroController(Carros carros){
        this.carros = carros;
    }

    @GetMapping("/api/carros/id/{id}")
    @ResponseBody()
    public ResponseEntity getCarroById(@PathVariable Integer id){
        Optional<Carro> carro = carros.findById(id);
        if(carro.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carro);
    }

    @GetMapping("/api/carros")
    @ResponseBody()
    public ResponseEntity getTodosCarros(){
        List<Carro> todosCarros = carros.findAll();
        if(todosCarros.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(todosCarros);
    }

    @GetMapping("/api/carros/modelo/{modelo}")
    @ResponseBody()
    public ResponseEntity getCarroByModelo(@PathVariable String modelo){
        List<Carro> carrosModelo = carros.findByModeloContaining(modelo);
        if(carrosModelo.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carrosModelo);
    }

    @GetMapping("/api/carros/placa/{placa}")
    @ResponseBody()
    public ResponseEntity getCarroByPlaca(@PathVariable String placa){
        List<Carro> carrosPlaca = carros.findByPlacaContaining(placa);
        if(carrosPlaca.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carrosPlaca);
    }

    @GetMapping("/api/carros/valor-maior-que/{valor}")
    @ResponseBody()
    public ResponseEntity getCarroByValorMaiorQue(@PathVariable Float valor){
        List<Carro> carrosValor = carros.findByValorDiariaGreaterThan(valor);
        if(carrosValor.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carrosValor);
    }

    @GetMapping("/api/carros/valor-menor-que/{valor}")
    @ResponseBody()
    public ResponseEntity getCarroByValorMenorQue(@PathVariable Float valor){
        List<Carro> carrosValor = carros.findByValorDiariaLessThan(valor);
        if(carrosValor.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carrosValor);
    }

    @GetMapping("/api/carros/disponiveis")
    @ResponseBody()
    public ResponseEntity getCarrosDisponiveis(){
        List<Carro> carrosDisponiveis = carros.findByDisponivel(true);
        if(carrosDisponiveis.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carrosDisponiveis);
    }

    @GetMapping("/api/carros/locados")
    @ResponseBody()
    public ResponseEntity getCarrosLocados(){
        List<Carro> carrosLocados = carros.findByDisponivel(false);
        if(carrosLocados.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carrosLocados);
    }

    @PostMapping("/api/carros/id")
    @ResponseBody
    public ResponseEntity saveNovoCarro(@RequestBody Carro carro){
        Carro carroSalvo = carros.save(carro);
        return ResponseEntity.ok(carroSalvo);
    }

}
