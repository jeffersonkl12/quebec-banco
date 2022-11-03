package org.example.model;

public interface OperacaoConta {

    public Double sacar(Double valor);
    public void depositar(Double valor);
    public void transferir(Conta destino,Double valor);
    public String extratoConta();
}
