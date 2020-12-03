package br.com.viniciuspenha.contacliente.exception;

public class ClienteNotFoundException extends Exception {

    public ClienteNotFoundException() {
        super("Cliente nao encontrado!");
    }
}