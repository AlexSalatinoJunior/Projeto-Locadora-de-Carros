package com.locadora.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    private String cnh;
    private boolean administrador;

    public Usuario(){
    }

    public Usuario(String nome){
        this.nome = nome;
    }

    public Usuario(String nome, int id){
        this.nome = nome;
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCNH() {
        return cnh;
    }
    public void setCNH(String cnh) {
        this.cnh = cnh;
    }

    public boolean isAdministrator() {
        return administrador;
    }

    @Override
    public String toString(){
        return "Usuario{"+
                "id="+id+
                ", nome: "+nome+"\'"+
                "}";
    }
}
