package org.example.model;

import org.example.exception.SaldoInvalidoException;

import java.util.Objects;
import java.util.Random;

public abstract class Conta implements OperacaoConta{
    protected static Integer numeroCount = 1;
    protected Integer agencia;
    protected Integer operacao;
    protected Integer numero;
    protected Double saldo;
    protected Double chequeEspecial;
    public Conta() {
        super();
        Random random = new Random();
        int aux = 1;
        this.agencia = 0;
        for(int x = 0; x < 4; x++, aux *= 10){
            this.agencia += (random.nextInt(9)) * aux;
        }
        this.numero = numeroCount++;
        this.saldo = 0.0;
        this.chequeEspecial = 0.0;
    }

    public void pagarChequeEspecial(){
        if(this.chequeEspecial > 0 && this.saldo >= this.chequeEspecial){
            this.saldo -= this.chequeEspecial;
            this.chequeEspecial = 0.0;
        }else{
            throw new SaldoInvalidoException("saldo insuficiente!");
        }
    }
    public Double sacaChequeEspecial(){
        if(this.chequeEspecial == 0) {
            return this.chequeEspecial = 200.0;
        }else{
            throw new SaldoInvalidoException("cheque ja sacado");
        }
    }
   @Override
   public Double sacar(Double valor){
        if(valor > 0 && this.saldo > 0 && valor <= this.saldo && this.chequeEspecial == 0) {
            this.saldo -= valor;
            return valor;
        }
        throw new SaldoInvalidoException("operacao saque com saldo invalido");
   }
    @Override
    public void depositar(Double valor){
        if(valor > 0){
            if(this.chequeEspecial > 0) {
                if(this.chequeEspecial < valor){
                    valor -= this.chequeEspecial;
                    this.chequeEspecial = 0.0;
                }else{
                    this.chequeEspecial -= valor;
                    valor = 0.0;
                }
            }
            this.saldo += valor;
        }else{
            throw new SaldoInvalidoException("operacao depositar com saldo invalido");
        }
    }

    @Override
    public void transferir(Conta destino, Double valor) {
        if(valor > 0 && this.saldo > 0 && valor <= this.saldo){
            destino.saldo += sacar(valor);
        }else{
            throw new SaldoInvalidoException("operacao transferir com saldo invalido");
        }
    }

    @Override
    public String extratoConta() {
        return String.format("\n==========\nAgencia: %d\nOperacao: %d\nNumero: %d\nSaldo: %.2f\n==========\n",
                this.agencia,this.operacao,this.numero,this.saldo);
    }

    public Integer getAgencia() {
        return agencia;
    }

    public Integer getOperacao() {
        return operacao;
    }

    public Integer getNumero() {
        return numero;
    }

    public Double getSaldo() {
        return saldo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return Objects.equals(agencia, conta.agencia) && Objects.equals(operacao, conta.operacao) && Objects.equals(numero, conta.numero) && Objects.equals(saldo, conta.saldo) && Objects.equals(chequeEspecial, conta.chequeEspecial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agencia, operacao, numero, saldo, chequeEspecial);
    }

    @Override
    public String toString() {
        return "Conta{" +
                "agencia=" + agencia +
                ", operacao=" + operacao +
                ", numero=" + numero +
                ", saldo=" + saldo +
                ", chequeEspecial=" + chequeEspecial +
                '}';
    }
}
