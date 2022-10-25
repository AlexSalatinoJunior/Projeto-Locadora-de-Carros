package com.locadora.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.locadora.entity.Carro;

@Repository
public class Carros {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Carro salvarCarro(Carro carro){
        entityManager.persist(carro);
        return carro;
    }

    @Transactional
    public Carro atualizarCarro(Carro carro){
        entityManager.merge(carro);
        return carro;
    }

    @Transactional
    public void deletarCarro(Carro carro){
        entityManager.remove(carro);
    }

    @Transactional
    public void deletarCarro(Integer id){
        deletarCarro(entityManager.find(Carro.class, id));
    }

    @Transactional(readOnly = true)
    public List<Carro> buscarPorModelo(String modelo){
        String jpql = "select c from Carro c where c.modelo like :modelo";
        TypedQuery<Carro> query = entityManager.createQuery(jpql, Carro.class);
        query.setParameter("modelo", "%" + modelo + "%");
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Carro> buscarPorPlaca(String placa){
        String jpql = "select c from Carro c where c.placa like :placa";
        TypedQuery<Carro> query = entityManager.createQuery(jpql, Carro.class);
        query.setParameter("placa", "%" + placa + "%");
        return query.getResultList();
    }

    public List<Carro> listarTodos(){
        return entityManager.createQuery("from Carro", Carro.class).getResultList();
    }
}
