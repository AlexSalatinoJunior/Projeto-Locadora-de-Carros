package com.locadora.domain.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "USUARIOS")
@Data
@Setter
@Getter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private String login;
    private String nome;
    private String cnh;
    private String cpf;
    private String email;
    @OneToOne(cascade=CascadeType.PERSIST)
    private Address address;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Pedido> pedidosUsuario;

    public Usuario(){}

    public Usuario addPedido(Pedido pedido){
        pedidosUsuario.add(pedido);
        return this;
    }

    @Override
    public String toString(){
        return "Usuario{"+
        "id="+id+
        ", nome: "+nome+"\'"+
        "}";
    }
}
