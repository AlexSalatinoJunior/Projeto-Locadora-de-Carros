package com.locadora.rest.controller;

import com.locadora.domain.entity.Address;
import com.locadora.domain.entity.Cliente;
import com.locadora.domain.entity.Usuario;
import com.locadora.domain.repository.AddressRepository;
import com.locadora.domain.repository.Usuarios;
import com.locadora.exception.SenhaInvalidaException;
import com.locadora.rest.dto.ClienteDTO;
import com.locadora.rest.dto.CredenciaisDTO;
import com.locadora.rest.dto.TokenDTO;
import com.locadora.security.jwt.JwtService;
import com.locadora.service.impl.ClienteServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteController {

    private final ClienteServiceImpl clienteService;
    private final Usuarios usuariosRepository;
    private final AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(CREATED)
    public Cliente salvar(@RequestBody @Validated ClienteDTO dto){

        Cliente cliente = createCliente(dto);
        String senhaCriptografada = passwordEncoder.encode(cliente.getSenha());

        createUsuario(dto);
        createAddress(dto.getAddress());

        cliente.setSenha(senhaCriptografada);
        return clienteService.salvar(cliente);
    }

    private Address createAddress(Address dto) {
        Address address = new Address();
        address.setLogin(dto.getLogin());
        address.setCep(dto.getCep());
        address.setRua(dto.getRua());
        address.setCidade(dto.getCidade());
        address.setBairro(dto.getBairro());
        address.setEstado(dto.getEstado());
        address.setNumero(dto.getNumero());
        return addressRepository.save(address);
    }


    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais){
        try{
            Cliente cliente = Cliente
                    .builder()
                    .login(credenciais.getLogin())
                    .senha(credenciais.getSenha())
                    .build();
            UserDetails usuarioAutenticado = clienteService.autenticar(cliente);
            String token = jwtService.gerarToken(cliente);
            return new TokenDTO(cliente.getLogin(), token);
        }catch (UsernameNotFoundException | SenhaInvalidaException ex){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage());
        }
    }
    private Cliente createCliente(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setSenha(dto.getSenha());
        cliente.setLogin(dto.getLogin());
        return cliente;
    }
    public Usuario createUsuario(ClienteDTO dto){
        Usuario usuario = new Usuario();
        usuario.setLogin(dto.getLogin());
        usuario.setNome(dto.getNome());
        usuario.setCnh(dto.getCnh());
        usuario.setCpf(dto.getCpf());
        usuario.setEmail(dto.getLogin());
        return usuariosRepository.save(usuario);
    }
}