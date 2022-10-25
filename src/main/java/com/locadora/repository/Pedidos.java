package com.locadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.locadora.entity.Pedido;

@Repository
public interface Pedidos extends JpaRepository<Pedido, Integer> {

}
