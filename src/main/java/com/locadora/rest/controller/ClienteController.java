package com.locadora.rest.controller;

import com.locadora.domain.entity.Address;
import com.locadora.domain.entity.Cliente;
import com.locadora.domain.entity.Usuario;
import com.locadora.domain.repository.AddressRepository;
import com.locadora.domain.repository.ClienteRepository;
import com.locadora.domain.repository.Usuarios;
import com.locadora.exception.SenhaInvalidaException;
import com.locadora.rest.dto.ClienteDTO;
import com.locadora.rest.dto.CredenciaisDTO;
import com.locadora.rest.dto.TokenDTO;
import com.locadora.security.jwt.JwtService;
import com.locadora.service.impl.ClienteServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private final ClienteRepository clienteRepository;

    @PostMapping
    @ResponseStatus(CREATED)
    public Cliente salvar(@RequestBody @Validated ClienteDTO dto){

        Cliente cliente = createCliente(dto);
        String senhaCriptografada = passwordEncoder.encode(cliente.getSenha());
        createUsuario(dto);
        cliente.setSenha(senhaCriptografada);
        return clienteService.salvar(cliente);
    }

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais){
        try{
            Cliente cliente = Cliente
                    .builder()
                    .login(credenciais.getLogin())
                    .senha(credenciais.getSenha())
                    .id(usuariosRepository.findByLogin(credenciais.getLogin()).getId())
                    .build();
            clienteService.autenticar(cliente);
            String token = jwtService.gerarToken(cliente);
            return new TokenDTO(cliente.getLogin(), token, cliente.getId().toString());
        }catch (UsernameNotFoundException | SenhaInvalidaException ex){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage());
        }
    }

    @GetMapping("/id/{id}")
    private Cliente getClienteIdUsuario(@PathVariable Integer id){
        String login = usuariosRepository.findById(id).get().getLogin();
        return clienteRepository.findByLogin(login).get();
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
    private Cliente createCliente(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setSenha(dto.getSenha());
        cliente.setLogin(dto.getLogin());
        cliente.setAdmin(dto.getAdmin());
        return cliente;
    }
    public Usuario createUsuario(ClienteDTO dto){
        Usuario usuario = new Usuario();
        usuario.setLogin(dto.getLogin());
        usuario.setNome(dto.getNome());
        usuario.setCnh(dto.getCnh());
        usuario.setCpf(dto.getCpf());
        usuario.setEmail(dto.getLogin());
        usuario.setAddress(dto.getAddress());
        return usuariosRepository.save(usuario);
    }
}
