package com.locadora.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.locadora.entity.Pedido;
import com.locadora.entity.Usuario;

@Repository
public class Pedidos {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Pedido salvarPedido(Pedido pedido){
        entityManager.persist(pedido);
        return pedido;
    }

    @Transactional
    public Pedido atualizarPedido(Pedido pedido){
        entityManager.merge(pedido);
        return pedido;
    }

    @Transactional
    public void deletarPedido(Pedido pedido){
        entityManager.remove(pedido);
    }

    @Transactional
    public void deletarPedido(Integer id){
        deletarPedido(entityManager.find(Pedido.class, id));
    }

    @Transactional(readOnly = true)
    public List<Pedido> buscarPorCliente(Usuario usuario){
        String jpql = "select p from Pedido p where p.id_usuario like :usuario.id";
        TypedQuery<Pedido> query = entityManager.createQuery(jpql, Pedido.class);
        query.setParameter("usuario.id", "%" + usuario.getId() + "%");
        return query.getResultList();
    }

    public List<Pedido> listarTodos(){
        return entityManager.createQuery("from Pedido", Pedido.class).getResultList();
    }
}
