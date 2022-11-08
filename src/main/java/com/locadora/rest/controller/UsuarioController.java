package com.locadora.rest.controller;

import java.util.Optional;
import java.util.List;

import com.locadora.exception.RegraDeNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.locadora.domain.entity.Usuario;
import com.locadora.domain.repository.Usuarios;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private Usuarios usuarios;

    public UsuarioController(Usuarios usuarios){
        this.usuarios = usuarios;
    }

    @GetMapping("/id/{id}")
    public Usuario getUsuarioById(@PathVariable Integer id){
        return usuarios.findById(id).orElseThrow(
                () -> new RegraDeNegocioException("Usuario não encontrado")
        );
    }

    @GetMapping
    public List<Usuario> getUsuarios(){
        if(usuarios.findAll().isEmpty()){
            throw new RegraDeNegocioException("Usuarios não encontrados");
        }
        return usuarios.findAll();
    }

    @GetMapping("/cnh/{cnh}")
    public List<Usuario> getUsuariosByCnh(@PathVariable String cnh){
        if(usuarios.findByCnhContaining(cnh).isEmpty()){
            throw new RegraDeNegocioException("CNH não encontrada");
        }
        return usuarios.findByCnhContaining(cnh);
    }

    @GetMapping("/nome/{nome}")
    public List<Usuario> getUsuariosByNome(@PathVariable String nome){
        if(usuarios.findByNomeContaining(nome).isEmpty()){
            throw new RegraDeNegocioException("Nome não encontrado");
        }
        return usuarios.findByNomeContaining(nome);
    }

    @GetMapping("/admin/{admin}")
    public List<Usuario> getUsuariosByAdmin(@PathVariable Boolean admin){
        if (usuarios.findByAdministrador(admin).isEmpty()){
            throw new RegraDeNegocioException("Administradores não encontrados");
        }
        return usuarios.findByAdministrador(admin);
    }

    @PostMapping("/id")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario saveNovoUsuario(@RequestBody Usuario usuario){
        return usuarios.save(usuario);
    }
}
