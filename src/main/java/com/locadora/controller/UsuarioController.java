package com.locadora.controller;

import java.util.Optional;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id){
        Optional<Usuario> usuario = usuarios.findById(id);
        if(usuario.isPresent()){
            return ResponseEntity.ok(usuario.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/api/usuarios")
    @ResponseBody
    public ResponseEntity<Usuario> getUsuarios(){
        List<Usuario> todosUsuarios = usuarios.findAll();
        if(todosUsuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/usuarios/cnh/{cnh}")
    @ResponseBody
    public ResponseEntity<Usuario> getUsuariosByCnh(@PathVariable String cnh){
        List<Usuario> usuariosCNH = usuarios.findByCnhContaining(cnh);
        if(usuariosCNH.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/usuarios/nome/{nome}")
    @ResponseBody
    public ResponseEntity<Usuario> getUsuariosByNome(@PathVariable String nome){
        List<Usuario> usuariosNome = usuarios.findByNomeContaining(nome);
        if(usuariosNome.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/usuarios/admin/{admin}")
    @ResponseBody
    public ResponseEntity<Usuario> getUsuariosByAdmin(@PathVariable Boolean admin){
        List<Usuario> usuariosAdm = usuarios.findByAdministrador(admin);
        if(usuariosAdm.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
