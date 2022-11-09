package com.locadora.rest.controller;

import java.util.Optional;
import java.util.List;

import com.locadora.exception.RegraDeNegocioException;
import com.locadora.rest.dto.InformacoesUsuarioDTO;
import com.locadora.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.locadora.domain.entity.Usuario;
import com.locadora.domain.repository.Usuarios;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private Usuarios usuarios;
    private UsuarioService usuarioService;

    public UsuarioController(Usuarios usuarios, UsuarioService usuarioService){
        this.usuarios = usuarios;
        this.usuarioService = usuarioService;
    }

    //update for DTO to pass the actual car
    @GetMapping("/id/{id}")
    public InformacoesUsuarioDTO getUsuarioById(@PathVariable Integer id){
        return usuarioService
                .obterUsuario(id)
                .map(user -> converter(user))
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado")
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

    private InformacoesUsuarioDTO converter(Usuario user){

        if(user.getCarroAtual() == null){
            return InformacoesUsuarioDTO.builder()
                    .id(user.getId())
                    .nomeUsuario(user.getNome())
                    .cnh(user.getCnh())
                    .administrador(user.isAdministrador())
                    .build();
        }

        return InformacoesUsuarioDTO.builder()
                .id(user.getId())
                .nomeUsuario(user.getNome())
                .cnh(user.getCnh())
                .idCarro(user.getCarroAtual().getId())
                .modeloCarro(user.getCarroAtual().getModelo())
                .administrador(user.isAdministrador())
                .build();
    }
}
