package com.locadora.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.locadora.entity.Pedido;
import com.locadora.entity.Usuario;

@Repository
public interface Pedidos extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByUsuario(Usuario u);
}
