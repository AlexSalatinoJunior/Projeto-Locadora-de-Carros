package com.locadora.rest.controller;

import com.locadora.domain.entity.Cliente;
import com.locadora.exception.SenhaInvalidaException;
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
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(CREATED)
    public Cliente salvar(@RequestBody @Validated Cliente cliente ){
        String senhaCriptografada = passwordEncoder.encode(cliente.getSenha());
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
                    .build();
            UserDetails usuarioAutenticado = clienteService.autenticar(cliente);
            String token = jwtService.gerarToken(cliente);
            return new TokenDTO(cliente.getLogin(), token);
        }catch (UsernameNotFoundException | SenhaInvalidaException ex){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage());
        }
    }
}
