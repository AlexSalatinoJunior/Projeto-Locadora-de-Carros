package com.locadora.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.locadora.entity.Carro;
import com.locadora.repository.Carros;

@Controller
public class CarroController {

    private Carros carros;

    public CarroController(Carros carros){
        this.carros = carros;
    }

    @GetMapping("/api/carros/id/{id}")
    @ResponseBody()
    public ResponseEntity<Carro> getCarroById(@PathVariable Integer id){
        Optional<Carro> carro = carros.findById(id);
        if(carro.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/carros")
    @ResponseBody()
    public ResponseEntity<Carro> getTodosCarros(){
        List<Carro> todosCarros = carros.findAll();
        if(todosCarros.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/carros/modelo/{modelo}")
    @ResponseBody()
    public ResponseEntity<Carro> getCarroByModelo(@PathVariable String modelo){
        List<Carro> carrosModelo = carros.findByModeloContaining(modelo);
        if(carrosModelo.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/carros/placa/{placa}")
    @ResponseBody()
    public ResponseEntity<Carro> getCarroByPlaca(@PathVariable String placa){
        List<Carro> carrosPlaca = carros.findByPlacaContaining(placa);
        if(carrosPlaca.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/carros/valor-maior-que/{valor}")
    @ResponseBody()
    public ResponseEntity<Carro> getCarroByValorMaiorQue(@PathVariable Float valor){
        List<Carro> carrosValor = carros.findByValorDiariaGreaterThan(valor);
        if(carrosValor.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/carros/valor-menor-que/{valor}")
    @ResponseBody()
    public ResponseEntity<Carro> getCarroByValorMenorQue(@PathVariable Float valor){
        List<Carro> carrosValor = carros.findByValorDiariaLessThan(valor);
        if(carrosValor.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/carros/disponiveis")
    @ResponseBody()
    public ResponseEntity<Carro> getCarrosDisponiveis(){
        List<Carro> carrosDisponiveis = carros.findByDisponivel(true);
        if(carrosDisponiveis.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/carros/locados")
    @ResponseBody()
    public ResponseEntity<Carro> getCarrosLocados(){
        List<Carro> carrosLocados = carros.findByDisponivel(false);
        if(carrosLocados.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
