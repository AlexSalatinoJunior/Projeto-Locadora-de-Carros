package com.locadora.domain.repository;

import java.util.List;

import com.locadora.domain.enums.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.locadora.domain.entity.Carro;

@Repository
public interface Carros extends JpaRepository<Carro, Integer>{

    List<Carro> findByModeloContaining(String modelo);
    List<Carro> findByPlacaContaining(String placa);
    List<Carro> findByValorDiariaGreaterThan(Float valorDiaria);
    List<Carro> findByValorDiariaLessThan(Float valorDiaria);
    List<Carro> findByDisponivel(Boolean disponivel);

    List<Carro> findByCategoria(Categoria categoria);
}
