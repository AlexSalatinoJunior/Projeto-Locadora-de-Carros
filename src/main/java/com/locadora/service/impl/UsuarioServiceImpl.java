package com.locadora.service.impl;

import com.locadora.domain.entity.Usuario;
import com.locadora.domain.repository.Usuarios;
import com.locadora.service.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private Usuarios usuariosRepository;

    public UsuarioServiceImpl(Usuarios usuariosRepository){
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    public Optional<Usuario> obterUsuario(Integer id) {
        return usuariosRepository.findById(id);
    }
}
