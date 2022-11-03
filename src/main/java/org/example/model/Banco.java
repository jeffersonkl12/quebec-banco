package org.example.model;

import org.example.exception.DadosInvalidosExcepiton;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Banco {

    private List<Cliente> clientes;

    public Banco() {
        clientes = new ArrayList<>();
    }

    public boolean acessarConta(String cpf, String senha){
        return clientes.stream()
                .anyMatch((v)->v.getCpf().compareTo(cpf) == 0 && v.getSenha().compareTo(senha) == 0);
    }

    public void pagaChequeEspecial(String cpf,String senha){
        identificaCliente(cpf,senha).getConta().pagarChequeEspecial();
    }

    public Double sacaChequeEspecial(String cpf,String senha){
        return identificaCliente(cpf,senha).getConta().sacaChequeEspecial();
    }

    public void deletaConta(String cpf,String senha){
        if(clientes.stream().anyMatch((v)->v.getCpf().compareTo(cpf) == 0 && v.getSenha().compareTo(senha) == 0)){
            clientes = clientes.stream()
                    .filter((v)->v.getCpf().compareTo(cpf) != 0)
                    .collect(Collectors.toList());
        }else{
            throw new DadosInvalidosExcepiton("cliente inexistente");
        }
    }

    public Double saqueConta(String cpf,String senha,Double valor){
        Cliente cliente = identificaCliente(cpf,senha);
        return cliente.getConta().sacar(valor);
    }

    public String extratoBancario(String cpf,String senha){
        Cliente cliente = identificaCliente(cpf,senha);
        return cliente.getConta().extratoConta();
    }

    public void depositarDinheiro(String cpf,String senha,Double valor){
        Cliente cliente = identificaCliente(cpf,senha);
        cliente.getConta().depositar(valor);
    }

    public void transfereDinheiro(String cpf,String senha,int agencia, int operacao, int numero,Double valor){
        Cliente destino = clientes.stream()
                .filter((v)->{
                    if(v.getConta().agencia.compareTo(agencia) == 0
                            && v.getConta().operacao.compareTo(operacao) == 0
                            && v.getConta().numero.compareTo(numero) == 0){
                        return true;
                    }
                    return false;
                }).findFirst().orElseThrow();

        Cliente origem = identificaCliente(cpf,senha);

        if(destino.getConta().operacao == origem.getConta().operacao){
            origem.getConta().transferir(destino.getConta(),valor);
        }else{
            throw new DadosInvalidosExcepiton("Tipo de contas nao conrrespondente");
        }
    }

    public void criaContaCorrente(String nome, String sexo, LocalDate idade, String cpf,String senha){
        if(clientes.stream().allMatch((v)->{
            return v.getCpf().compareTo(cpf) != 0;
        })){
            Conta conta = new ContaCorrente();
            Cliente cliente = criaCliente(nome,sexo,idade,cpf,senha,conta);
            clientes.add(cliente);
        }else{
            throw new DadosInvalidosExcepiton("dados de clientes existentes");
        }

    }

    public void criaContaPoupanca(String nome, String sexo, LocalDate idade, String cpf,String senha){
        Conta conta = new ContaPoupanca();
        Cliente cliente = criaCliente(nome,sexo,idade,cpf,senha,conta);
        clientes.add(cliente);
    }

    private Cliente criaCliente(String nome, String sexo, LocalDate idade, String cpf,String senha,Conta conta) throws DadosInvalidosExcepiton{
        if(LocalDate.now().getYear() - idade.getYear() >= 18 && validaCpf(cpf)){
            Cliente cliente = new Cliente(nome,idade,sexo,cpf,senha,conta);
            return cliente;
        }else{
            throw new DadosInvalidosExcepiton("dados nao validos");
        }
    }

    private boolean validaCpf(String cpf){
        if(cpf.length() == 11){
            String validaCpf = cpf.substring(0,cpf.length() - 2);

            for(int y = 0; y < 2; y++){
                int aux = 10;
                Integer somaCpf = 0;
                for(int x = 0; x < 9; x++,aux--){
                    somaCpf +=  Character.getNumericValue(validaCpf.charAt(x)) * aux;
                }
                validaCpf = validaCpf.substring(1,validaCpf.length());

                if(somaCpf % 11 != 0){
                    validaCpf += 11 - (somaCpf % 11);
                }else{
                    validaCpf += 0;
                }
            }

            if(validaCpf.charAt(validaCpf.length()-1) == cpf.charAt(cpf.length()-1)
            &&validaCpf.charAt(validaCpf.length()-2) == cpf.charAt(cpf.length()-2)){
                return true;
            }
            return false;
        }
        return false;
    }

    private Cliente identificaCliente(String cpf,String senha){
        return clientes.stream()
                .filter((v)->v.getCpf().compareTo(cpf) == 0 && v.getSenha().compareTo(senha) == 0)
                .findFirst().orElseThrow();
    }

}
