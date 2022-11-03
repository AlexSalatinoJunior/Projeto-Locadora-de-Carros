package com.locadora.controller;

import java.util.Optional;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.locadora.entity.Usuario;
import com.locadora.repository.Usuarios;

@Controller
public class UsuarioController {

    private Usuarios usuarios;

    public UsuarioController(Usuarios usuarios){
        this.usuarios = usuarios;
    }

    @GetMapping("/api/usuarios/id/{id}")
    @ResponseBody
    public ResponseEntity getUsuarioById(@PathVariable Integer id){
        Optional<Usuario> usuario = usuarios.findById(id);
        if(usuario.isPresent()){
            return ResponseEntity.ok(usuario.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/api/usuarios")
    @ResponseBody
    public ResponseEntity getUsuarios(){
        List<Usuario> todosUsuarios = usuarios.findAll();
        if(todosUsuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(todosUsuarios);
    }

    @GetMapping("/api/usuarios/cnh/{cnh}")
    @ResponseBody
    public ResponseEntity getUsuariosByCnh(@PathVariable String cnh){
        List<Usuario> usuariosCNH = usuarios.findByCnhContaining(cnh);
        if(usuariosCNH.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuariosCNH);
    }

    @GetMapping("/api/usuarios/nome/{nome}")
    @ResponseBody
    public ResponseEntity getUsuariosByNome(@PathVariable String nome){
        List<Usuario> usuariosNome = usuarios.findByNomeContaining(nome);
        if(usuariosNome.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuariosNome);
    }

    @GetMapping("/api/usuarios/admin/{admin}")
    @ResponseBody
    public ResponseEntity getUsuariosByAdmin(@PathVariable Boolean admin){
        List<Usuario> usuariosAdm = usuarios.findByAdministrador(admin);
        if(usuariosAdm.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuariosAdm);
    }

    @PostMapping("/api/usuarios/id")
    @ResponseBody
    public ResponseEntity saveNovoUsuario(@RequestBody Usuario usuario){
        Usuario usuarioNovo = usuarios.save(usuario);
        return ResponseEntity.ok(usuarioNovo);
    }
}
