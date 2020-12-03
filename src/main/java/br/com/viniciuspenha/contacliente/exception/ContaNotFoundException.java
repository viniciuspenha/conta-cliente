package br.com.viniciuspenha.contacliente.exception;

public class ContaNotFoundException extends Exception {

    public ContaNotFoundException() {
        super("Conta nao encontrado!");
    }
}