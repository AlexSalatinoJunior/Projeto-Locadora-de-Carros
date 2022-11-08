package com.locadora.domain.entity;

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
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CARRO")
    private Carro carroAtual;
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

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public Carro getCarroAtual() {
        return carroAtual;
    }

    public void setCarroAtual(Carro carroAtual) {
        this.carroAtual = carroAtual;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public void setPedidosUsuario(Set<Pedido> pedidosUsuario) {
        this.pedidosUsuario = pedidosUsuario;
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
