package com.locadora.rest.controller;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

import com.locadora.domain.enums.Categoria;
import org.springframework.web.bind.annotation.*;
import com.locadora.domain.entity.Carro;
import com.locadora.domain.repository.Carros;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/carros")
public class CarroController {

    private Carros carros;

    public CarroController(Carros carros){
        this.carros = carros;
    }

    @GetMapping("/id/{id}")
    public Carro getCarroById(@PathVariable Integer id){
        return carros.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(NOT_FOUND, "Carro não encontrado")
                );
    }

    @GetMapping
    public List<Carro> getTodosCarros(){
        if(carros.findAll().isEmpty()){
            throw new ResponseStatusException(NOT_FOUND, "Lista de carros vazias");
        }
        return carros.findAll();
    }

    @GetMapping("/modelo/{modelo}")
    public List<Carro> getCarroByModelo(@PathVariable String modelo){
        if(carros.findByModeloContaining(modelo).isEmpty()){
            throw new ResponseStatusException(NOT_FOUND, "Modelo não encontrado");
        }
        return carros.findByModeloContaining(modelo);
    }

    @GetMapping("/placa/{placa}")
    public List<Carro> getCarroByPlaca(@PathVariable String placa){
        if(carros.findByPlacaContaining(placa).isEmpty()){
            throw new ResponseStatusException(NOT_FOUND, "Placa não encontrada");
        }
        return carros.findByPlacaContaining(placa);
    }

    @GetMapping("/valor-maior-que/{valor}")
    public List<Carro> getCarroByValorMaiorQue(@PathVariable Float valor){
        if(carros.findByValorDiariaGreaterThan(valor).isEmpty()){
            throw new ResponseStatusException(NOT_FOUND, "Sem carros com valor maior");
        }
        return carros.findByValorDiariaGreaterThan(valor);
    }

    @GetMapping("/valor-menor-que/{valor}")
    public List<Carro> getCarroByValorMenorQue(@PathVariable Float valor){
        if(carros.findByValorDiariaLessThan(valor).isEmpty()){
            throw new ResponseStatusException(NOT_FOUND, "Sem carros com valor menor");
        }
        return carros.findByValorDiariaLessThan(valor);
    }

    @GetMapping("/disponiveis")
    public List<Carro> getCarrosDisponiveis(){
        if (carros.findByDisponivel(true).isEmpty()){
            throw new ResponseStatusException(NOT_FOUND, "Sem carros disponiveis");
        }

        return carros.findByDisponivel(true);
    }

    @GetMapping("/locados")
    public List<Carro> getCarrosLocados(){
        if(carros.findByDisponivel(false).isEmpty()){
            throw new ResponseStatusException(NOT_FOUND, "Sem carros locados");
        }
        return carros.findByDisponivel(false);
    }

    @GetMapping("/categoria/{categoria}")
    public List<Carro> getCarroByCategoria(@PathVariable Categoria categoria){
        if(carros.findByCategoria(categoria).isEmpty()){
            throw new ResponseStatusException(NOT_FOUND, "Sem carros dessa categoria");
        }
        return carros.findByCategoria(categoria);
    }

    @PutMapping("/id/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Carro carro){
        carros.findById(id)
                .map(carroExistente ->{
                    carro.setId(carroExistente.getId());
                    carros.save(carro);
                    return carro;
                }).orElseThrow(
                        () -> new ResponseStatusException(NOT_FOUND, "Carro não encontrado")
                );
    }

    @PostMapping("/id")
    @ResponseStatus(CREATED)
    public Carro saveNovoCarro(@RequestBody Carro carro){
        return carros.save(carro);
    }

}
