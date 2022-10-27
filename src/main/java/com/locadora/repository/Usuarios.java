package com.locadora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.locadora.entity.Usuario;


@Repository
public interface Usuarios extends JpaRepository<Usuario, Integer>{

    List<Usuario> findByNomeContaining(String nome);
    List<Usuario> findByCnhContaining(int cnh);
    List<Usuario> findByAdministradorContaining(boolean administrador);

    @Query("select u from Usuario u left join fetch u.pedidosUsuario where u.id= :id")
    Usuario findClienteFetchPedidos(@Param("id") Integer id);
}
