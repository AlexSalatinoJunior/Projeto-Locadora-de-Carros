package com.locadora.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.locadora.domain.entity.Carro;
import com.locadora.domain.entity.Pedido;
import com.locadora.domain.entity.Usuario;

@Repository
public interface Pedidos extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByUsuario(Usuario u);
    List<Pedido> findByCarro(Carro c);
    List<Pedido> findByValorTotalGreaterThan(Double valor);
    List<Pedido> findByValorTotalLessThan(Double valor);
    List<Pedido> findByUsuario(Optional<Usuario> usuario);
    List<Pedido> findByCarro(Optional<Carro> carro);
}
