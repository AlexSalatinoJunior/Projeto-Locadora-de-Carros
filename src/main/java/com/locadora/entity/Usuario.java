package com.locadora.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String usuario;
    private String cnh;
    private boolean administrador;

    public Usuario(){
    }

    public Usuario(String usuario){
        this.usuario = usuario;
    }

    public Usuario(String usuario, int id){
        this.usuario = usuario;
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
                ", nome: "+usuario+"\'"+
                "}";
    }
}
