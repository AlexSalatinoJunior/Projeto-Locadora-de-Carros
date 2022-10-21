package com.locadora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.services.UserService;

@SpringBootApplication
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