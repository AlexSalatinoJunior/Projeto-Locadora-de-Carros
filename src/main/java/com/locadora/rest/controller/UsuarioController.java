package com.locadora.rest.controller;

import java.util.List;
import com.locadora.domain.entity.Address;
import com.locadora.domain.entity.Cliente;
import com.locadora.domain.repository.AddressRepository;
import com.locadora.domain.repository.ClienteRepository;
import com.locadora.exception.RegraDeNegocioException;
import com.locadora.rest.dto.InformacoesUsuarioDTO;
import com.locadora.rest.dto.LoginInfoDTO;
import com.locadora.rest.dto.UsuarioDTO;
import com.locadora.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.locadora.domain.entity.Usuario;
import com.locadora.domain.repository.Usuarios;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

    private final Usuarios usuarios;
    private final UsuarioService usuarioService;
    private final AddressRepository addressRepository;
    private final ClienteRepository clienteRepository;



    @GetMapping("/id/{id}")
    public InformacoesUsuarioDTO getUsuarioById(@PathVariable Integer id){
        return usuarioService
                .obterUsuario(id)
                .map(user -> converter(user))
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado")
                );
    }

    @GetMapping("{id}")
    public LoginInfoDTO getLoginById(@PathVariable Integer id){
        LoginInfoDTO login = new LoginInfoDTO();
        login.setLogin(usuarios.findById(id).get().getLogin());
        return login;
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

    @GetMapping("/login/{login}")
    public Usuario getUsuariosByLogin(@PathVariable String login){
        if(usuarios.findByLogin(login) == null){
            throw new RegraDeNegocioException("Nome não encontrado");
        }
        return usuarios.findByLogin(login);
    }

    @PatchMapping("/id/{id}")
    public Usuario updateAddress(@PathVariable Integer id, @RequestBody Address address){
        Usuario originalUsuario = usuarios.findById(id).orElseThrow(
                () -> new RegraDeNegocioException("Usuario não encontrado")
        );
        Address originalAddressFromUsuario = addressRepository
                .findById(originalUsuario.getAddress().getId()).orElseThrow(
                        () -> new RegraDeNegocioException("Endereco não encontrado")
                );
        address.setId(originalAddressFromUsuario.getId());
        addressRepository.save(address);
        originalUsuario.setAddress(address);
        System.out.println(originalUsuario);
        return usuarios.save(originalUsuario);
    }

    @PostMapping("/id")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario saveNovoUsuario(@RequestBody Usuario usuario){
        return usuarios.save(usuario);
    }

    @PutMapping("/id/{id}")
    public Usuario update(@PathVariable Integer id, @RequestBody UsuarioDTO usuario){
        Usuario usuarioExistente = usuarios.findById(id).get();
        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setCpf(usuario.getCpf());
        usuarioExistente.setCnh(usuario.getCnh());
        usuarioExistente.setLogin(usuario.getLogin());
        Cliente cliente = clienteRepository.findByLogin(usuario.getLogin()).get();
        cliente.setSenha(usuario.getSenha());
        cliente.setAdmin(usuario.getAdmin());
        clienteRepository.save(cliente);
        return usuarios.save(usuarioExistente);
    }

    private InformacoesUsuarioDTO converter(Usuario user){
        return InformacoesUsuarioDTO.builder()
                .id(user.getId())
                .nomeUsuario(user.getNome())
                .cpf(user.getCpf())
                .cnh(user.getCnh())
                .email(user.getEmail())
                .address(user.getAddress())
                .build();
    }
}
