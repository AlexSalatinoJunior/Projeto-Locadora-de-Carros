package com.locadora.exception;

public class PedidoNaoEncontradoException extends RuntimeException {

    public PedidoNaoEncontradoException(String message) {
        super(message);
    }
}
