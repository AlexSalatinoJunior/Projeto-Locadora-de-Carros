package com.locadora.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.locadora.domain.entity.Cliente;

@Repository
public interface Clientes extends JpaRepository<Cliente, Integer>{

}
