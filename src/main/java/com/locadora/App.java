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
            usuario.setUsuario("Alex");
            usuarios.salvarUser(usuario);

            Usuario usuario2 = new Usuario();
            usuario2.setUsuario("João");
            usuarios.salvarUser(usuario2);

            Usuario usuario3 = new Usuario();
            usuario3.setUsuario("André");
            usuarios.salvarUser(usuario3);

            System.out.println("Listando todos usuarios");
            List<Usuario> todosUsuarios = usuarios.listarTodos();
            if(todosUsuarios.isEmpty()){
                System.out.println("Nenhum usuário cadastrado.");
            } else todosUsuarios.forEach(System.out::println);

            System.out.println("Atualizando usuarios");
            todosUsuarios.forEach(u -> {
                u.setUsuario(u.getUsuario() + " atualizado");
                usuarios.atualizarUser(u);
            });

            System.out.println("Buscando usuario pelo nome");
            usuarios.buscarPorNome("Alex").forEach(System.out::println);

            System.out.println("Deletando usuarios");
            usuarios.deletarUser(1);
            usuarios.deletarUser(2);
            usuarios.deletarUser(3);

            System.out.println("Listando todos usuarios");
            todosUsuarios = usuarios.listarTodos();
            if(todosUsuarios.isEmpty()){
                System.out.println("Nenhum usuário cadastrado.");
            } else todosUsuarios.forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}