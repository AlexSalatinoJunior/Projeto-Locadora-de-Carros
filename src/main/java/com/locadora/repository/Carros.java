package com.locadora.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.locadora.entity.Carro;

@Repository
public interface Carros extends JpaRepository<Carro, Integer>{

    List<Carro> findByModeloContaining(String modelo);
    List<Carro> findByPlacaContaining(String placa);
    List<Carro> findByValorDiariaGreaterThan(Double valorDiaria);
    List<Carro> findByValorDiariaLessThan(Double valorDiaria);
    List<Carro> findByDisponivelContaining(Boolean disponivel);
}
