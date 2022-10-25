package com.locadora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.locadora.entity.Usuario;


@Repository
public interface Usuarios extends JpaRepository<Usuario, Integer>{

    List<Usuario> findByNomeContaining(String nome);

}
