package com.locadora.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

@Entity
@Table(name = "USUARIOS")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private String nome;
    private String cnh;
    private boolean administrador = false;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Pedido> pedidosUsuario;

    public Usuario(){}

    public Usuario(String nome, String cnh){
        this.nome = nome;
        this.cnh = cnh;
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

        public Set<Pedido> getPedidosUsuario() {
            return pedidosUsuario;
        }

}
