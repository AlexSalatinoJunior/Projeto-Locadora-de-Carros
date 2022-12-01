package com.locadora.rest.controller;

import com.locadora.domain.entity.Address;
import com.locadora.domain.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressRepository addressRepository;

    @GetMapping
    public List<Address> obterEndereco(){
        return this.addressRepository.findAll();
    }
}
