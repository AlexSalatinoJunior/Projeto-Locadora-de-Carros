package com.locadora.rest.controller;

import com.locadora.domain.entity.Address;
import com.locadora.domain.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AddressController {

    private final AddressRepository addressRepository;

    @GetMapping("/login/{login}")
    public List<Address> obterEndereco(@PathVariable String login){
        return this.addressRepository.findByLogin(login);
    }

    @PostMapping
    public Address novoEndereco(@RequestBody Address address){
        return this.addressRepository.save(address);
    }

    @DeleteMapping("/{id}")
    public void deleteEndereco(@PathVariable Integer id){
        this.addressRepository.deleteById(id);
    }
}
