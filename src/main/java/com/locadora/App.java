package com.locadora;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import com.locadora.entity.Pedido;
import com.locadora.entity.Usuario;
import com.locadora.repository.Pedidos;
import com.locadora.repository.Usuarios;

@SpringBootApplication()
@RestController
public class App{

    @Bean
    public CommandLineRunner init(@Autowired Usuarios usuarios, @Autowired Pedidos pedidos){
        return args -> {
            System.out.println("Salvando usuarios");

            Usuario usuario = new Usuario();
            usuario.setNome("Alex");
            usuarios.save(usuario);

            Usuario usuario2 = new Usuario();
            usuario2.setNome("Andre");
            usuarios.save(usuario2);

            Usuario usuario3 = new Usuario();
            usuario3.setNome("Joao");
            usuarios.save(usuario3);

            Pedido p = new Pedido();
            p.setUsuario(usuario);
            pedidos.save(p);

            Pedido p2 = new Pedido();
            p2.setUsuario(usuario2);
            pedidos.save(p2);

            Pedido p3 = new Pedido();
            p3.setUsuario(usuario3);
            pedidos.save(p3);

            Usuario fulano = usuarios.findClienteFetchPedidos(3);
            System.out.println(fulano.getPedidosUsuario());

            System.out.println(pedidos.findByUsuario(usuario));
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}