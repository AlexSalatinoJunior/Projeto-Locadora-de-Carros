package com.locadora;

import static com.locadora.domain.enums.Categoria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.locadora.domain.entity.Carro;
import com.locadora.domain.repository.Carros;
import com.locadora.domain.repository.Pedidos;
import com.locadora.domain.repository.Usuarios;

@SpringBootApplication()
public class App{

    @Bean
    public CommandLineRunner init(@Autowired Usuarios usuarios, @Autowired Pedidos pedidos, @Autowired Carros carros){
        return args -> {
            Carro carro = new Carro("Argo", "abc-1234", SUV, 150, "https://s2.glbimg.com/SnbghEUAAP3LQ2-bQMzzshaZzGo=/0x0:6000x3940/1008x0/smart/filters:strip_icc()/i.s3.glbimg.com/v1/AUTH_59edd422c0c84a879bd37670ae4f538a/internal_photos/bs/2020/d/x/mRAVtPRBqQp245yVLGSg/hgt18dianteira.jpeg");
            Carro carro2 = new Carro("Onix", "xyz-9876", HATCH, 120, "https://www.chevrolet.com.br/content/dam/chevrolet/mercosur/brazil/portuguese/index/cars/2023-onix/colorizer/01-images/colorizer-onix-my23-azul-seeker-3-4-frente.jpg?imwidth=960");
            Carro carro3 = new Carro("Versa", "hij-8877", SEDAN, 250, "https://cdn.autopapo.com.br/box/uploads/2020/10/27152022/nissan_versa_2021-3-732x488.jpg");
            carros.save(carro);
            carros.save(carro2);
            carros.save(carro3);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}