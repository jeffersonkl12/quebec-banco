package org.example.model;

import java.time.LocalDate;
import java.util.Objects;

public class Cliente {

    private String nome;
    private LocalDate idade;
    private Genero sexo;
    private String cpf;
    private String senha;
    private Conta conta;

    public Cliente() {
    }

    public Cliente(String nome, LocalDate idade, String sexo, String cpf, String senha,Conta conta) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = Genero.valueOf(sexo.toUpperCase());
        this.cpf = cpf;
        this.senha = senha;
        this.conta = conta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getIdade() {
        return idade;
    }

    public void setIdade(LocalDate idade) {
        this.idade = idade;
    }

    public Genero getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = Genero.valueOf(sexo);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Conta getConta() {
        return conta;
    }

    private void setConta(Conta conta) {
        this.conta = conta;
    }

    public void setSexo(Genero sexo) {
        this.sexo = sexo;
    }

    public String getSenha() {
        return senha;
    }

    private void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(nome, cliente.nome) && Objects.equals(idade, cliente.idade) && sexo == cliente.sexo && Objects.equals(cpf, cliente.cpf) && Objects.equals(senha, cliente.senha) && Objects.equals(conta, cliente.conta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, idade, sexo, cpf, senha, conta);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", sexo=" + sexo +
                ", cpf='" + cpf + '\'' +
                ", senha='" + senha + '\'' +
                ", conta=" + conta +
                '}';
    }

    private enum Genero{
        MASCULINO,
        FEMININO
    }
}
