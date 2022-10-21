package com.locadora.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.springframework.stereotype.Service;

import com.locadora.model.User;
import com.locadora.repository.UserRepository;

@Service
public class UserService {

    UserRepository ur;
    public static final String GET_CEP = "http://viacep.com.br/ws/";

    public UserService(UserRepository ur){
        this.ur = ur;
    }

    public void salvarCliente(User cliente){
        validarCliente(cliente);
        ur.persistir(cliente);
    }

    public void validarCliente(User cliente){
        //realiza validações
        validarCEP(cliente.cep);
    }

    public static String validarCEP(int cep){
        HttpClient clientHTTP = HttpClient.newHttpClient();
        HttpRequest requestHTTP = HttpRequest.newBuilder().GET().uri(URI.create(GET_CEP+cep+"/json/")).build();
        HttpResponse <String> responseHTTP = null;
        try {
            responseHTTP = clientHTTP.send(requestHTTP,HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(responseHTTP == null) return "Ocorreu um erro";
        if(responseHTTP.body().contains("erro")) return "Cep inválido";
        return responseHTTP.body();
    }
}
