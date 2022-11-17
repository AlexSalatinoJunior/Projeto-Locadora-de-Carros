package com.locadora.rest.controller;

import com.locadora.exception.PedidoNaoEncontradoException;
import com.locadora.exception.RegraDeNegocioException;
import com.locadora.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraDeNegocioException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErrors handleRegrasNegocioException(RegraDeNegocioException ex){
        String messageErro = ex.getMessage();
        return new ApiErrors(messageErro);
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlePedidoNotFoundException(PedidoNaoEncontradoException ex){
        String messageErro = ex.getMessage();
        return new ApiErrors(messageErro);
    }
}
