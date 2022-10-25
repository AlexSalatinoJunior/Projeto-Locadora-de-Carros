package com.locadora.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.locadora.entity.Usuario;

@Repository
public class Usuarios {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Usuario salvarUser(Usuario usuario){
        entityManager.persist(usuario);
        return usuario;
    }

    @Transactional
    public Usuario atualizarUser(Usuario usuario){
        entityManager.merge(usuario);
        return usuario;
    }

    @Transactional
    public void deletarUser(Usuario usuario){
        entityManager.remove(usuario);
    }

    @Transactional
    public void deletarUser(Integer id){
        deletarUser(entityManager.find(Usuario.class, id));
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarPorNome(String usuario){
        String jpql = "select u from Usuario u where u.usuario like :usuario";
        TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
        query.setParameter("usuario", "%" + usuario + "%");
        return query.getResultList();
    }

    public List<Usuario> listarTodos(){
        return entityManager.createQuery("from Usuario", Usuario.class).getResultList();
    }
}
