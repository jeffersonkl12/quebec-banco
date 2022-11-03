package org.example.exception;

public class SaldoInvalidoException extends RuntimeException{
    public SaldoInvalidoException(String msg) {
        super(msg);
    }
}
