package br.com.viniciuspenha.contacliente.exception;

public class SaldoInsuficienteException extends Exception {

    public SaldoInsuficienteException() {
        super("Saldo insuficiente!");
    }
}