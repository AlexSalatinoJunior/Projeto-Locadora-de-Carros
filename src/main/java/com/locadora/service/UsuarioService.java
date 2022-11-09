package com.locadora.service;

import com.locadora.domain.entity.Usuario;

import java.util.Optional;

public interface UsuarioService {

    Optional<Usuario> obterUsuario(Integer id);
}
