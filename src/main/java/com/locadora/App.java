package com.locadora;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.services.UserService;

@SpringBootApplication()
@RestController
public class App{

    @GetMapping("/hello")
    public String hello(){
        return "Hello world!";
    }

    @GetMapping(path = {"/cep"})
    public String consultaCep(){
        return UserService.validarCEP(83331170);
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}