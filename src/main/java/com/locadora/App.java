package com.locadora;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.entity.Usuario;
import com.locadora.repository.Usuarios;

@SpringBootApplication()
@RestController
public class App{

    @Bean
    public CommandLineRunner init(@Autowired Usuarios usuarios){
        return args -> {
            System.out.println("Salvando usuarios");
            Usuario usuario = new Usuario();
            usuario.setNome("Alex");
            usuarios.save(usuario);

            Usuario usuario2 = new Usuario();
            usuario2.setNome("João");
            usuarios.save(usuario2);

            Usuario usuario3 = new Usuario();
            usuario3.setNome("André");
            usuarios.save(usuario3);

            List<Usuario> todosUsuarios = usuarios.findAll();
            System.out.println("Listando todos usuarios");
            if(todosUsuarios.isEmpty()){
                System.out.println("Nenhum usuário cadastrado.");
            } else todosUsuarios.forEach(System.out::println);

            System.out.println("Atualizando usuarios");
            todosUsuarios.forEach(u -> {
                u.setNome(u.getNome() + " atualizado");
                usuarios.save(u);
            });

            System.out.println("Listando todos usuarios");
            todosUsuarios = usuarios.findAll();
            if(todosUsuarios.isEmpty()){
                System.out.println("Nenhum usuário cadastrado.");
            } else todosUsuarios.forEach(System.out::println);

            System.out.println("Buscando usuario pelo nome");
            usuarios.findByNomeContaining("ex").forEach(System.out::println);

            System.out.println("Deletando usuarios");
            usuarios.deleteById(1);
            usuarios.deleteById(2);
            usuarios.deleteById(3);

            System.out.println("Listando todos usuarios");
            todosUsuarios = usuarios.findAll();
            if(todosUsuarios.isEmpty()){
                System.out.println("Nenhum usuário cadastrado.");
            } else todosUsuarios.forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}