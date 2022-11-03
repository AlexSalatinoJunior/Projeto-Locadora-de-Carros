package com.locadora;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import com.locadora.entity.Carro;
import com.locadora.entity.Pedido;
import com.locadora.entity.Usuario;
import com.locadora.repository.Carros;
import com.locadora.repository.Pedidos;
import com.locadora.repository.Usuarios;

@SpringBootApplication()
@RestController
public class App{

    @Bean
    public CommandLineRunner init(@Autowired Usuarios usuarios, @Autowired Pedidos pedidos, @Autowired Carros carros){
        return args -> {
            Usuario usuario = new Usuario("Alex", "12345678910");
            Usuario usuario2 = new Usuario("Andre", "10987654321");
            Usuario usuario3 = new Usuario("Joao", "11223344556");

            Carro carro = new Carro("Argo", "abc-1234", 150);
            Carro carro2 = new Carro("Onix", "xyz-9876", 120);
            Carro carro3 = new Carro("Versa", "hij-8877", 250);

            Pedido pedido = new Pedido(carro2, usuario, 5);
            Pedido pedido2 = new Pedido(carro3, usuario2, 10);

            usuarios.save(usuario);
            usuarios.save(usuario2);
            usuarios.save(usuario3);
            carros.save(carro);
            carros.save(carro2);
            carros.save(carro3);
            pedidos.save(pedido);
            pedidos.save(pedido2);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}