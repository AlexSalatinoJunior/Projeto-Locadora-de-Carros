package com.locadora.domain.repository;

import java.util.Collection;
import java.util.List;

import com.locadora.rest.dto.InformacoesUsuarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.locadora.domain.entity.Usuario;


@Repository
public interface Usuarios extends JpaRepository<Usuario, Integer>{

    List<Usuario> findByNomeContaining(String nome);
    List<Usuario> findByCnhContaining(String cnh);

    @Query("select u from Usuario u left join fetch u.pedidosUsuario where u.id= :id")
    Usuario findClienteFetchPedidos(@Param("id") Integer id);

    Usuario findByLogin(String nome);
}
