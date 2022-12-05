package com.locadora.rest.controller;

import com.locadora.domain.entity.Address;
import com.locadora.domain.entity.Cliente;
import com.locadora.domain.entity.Usuario;
import com.locadora.domain.repository.AddressRepository;
import com.locadora.domain.repository.Clientes;
import com.locadora.domain.repository.Usuarios;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AddressController {

    private final AddressRepository addressRepository;
    private final Usuarios usuariosRepository;

    @GetMapping("/id/{id}")
    public List<Address> obterEndereco(@PathVariable Integer id){
        String login = usuariosRepository.findById(id).get().getLogin();
        return addressRepository.findByLogin(login);
    }

    @PostMapping
    public Address novoEndereco(@RequestBody Address address){
        return addressRepository.save(address);
    }

    @DeleteMapping("/{id}")
    public void deleteEndereco(@PathVariable Integer id){
        this.addressRepository.deleteById(id);
    }
}
