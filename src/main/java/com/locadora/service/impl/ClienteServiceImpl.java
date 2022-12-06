package com.locadora.service.impl;

import com.locadora.domain.entity.Cliente;
import com.locadora.domain.repository.ClienteRepository;
import com.locadora.exception.SenhaInvalidaException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements UserDetailsService {

    private final PasswordEncoder encoder;
    private final ClienteRepository clienteRepository;


    public Cliente salvar(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public UserDetails autenticar(Cliente cliente){
        UserDetails user = loadUserByUsername(cliente.getLogin());
        boolean senhasBatem = encoder.matches(cliente.getSenha(), user.getPassword());
        if(senhasBatem){
            return user;
        }
        throw new SenhaInvalidaException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByLogin(username).get();

        String[] roles = cliente.isAdmin() ?
                new String[]{"ADMIN", "USER"} :
                new String[]{"USER"};

        return User
                .builder()
                .username(cliente.getLogin())
                .password(cliente.getSenha())
                .roles(roles)
                .build();
    }
}
