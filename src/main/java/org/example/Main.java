package org.example;

import org.example.exception.DadosInvalidosExcepiton;
import org.example.exception.SaldoInvalidoException;
import org.example.model.Banco;
import org.example.model.Conta;
import org.example.model.ContaCorrente;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Banco banco = new Banco();
        String cpf = "";
        String senha = "";
        Double valor;
        int opcao;
        Integer agencia,operacao,numero;
        try {
            while (true) {
                System.out.println("==================\n" +
                        "----Sistema Banco Do Proletariado----\n");
                if (!cpf.isBlank() && !cpf.isEmpty() && senha != null && !senha.isBlank()) {
                    System.out.println("Escolha uma das nossas operacoes: \n" +
                            "1 - Sacar dinheiro\n" +
                            "2 - Depositar dinheiro\n" +
                            "3 - Transferir dinheiro\n" +
                            "4 - Extrato bancario\n" +
                            "5 - Cheque especial\n" +
                            "6 - Paga cheque especial\n" +
                            "7 - Cancelar conta\n" +
                            "0 - Sair");
                    opcao = sc.nextInt();
                    sc.nextLine();
                    switch (opcao) {
                        case 1:
                            System.out.println("Digite o valor a ser sacado: \n");
                            valor = sc.nextDouble();
                            System.out.println("Dinheiro sacado: " + banco.saqueConta(cpf, senha, valor));
                            break;
                        case 2:
                            System.out.println("Digite o valor para depositar: \n");
                            valor = sc.nextDouble();
                            banco.depositarDinheiro(cpf, senha, valor);
                            System.out.println("Deposito efetuado com sucesso!");
                            break;
                        case 3:
                            System.out.println("Digite a agencia da conta destino: \n");
                            agencia = sc.nextInt();
                            System.out.println("Digite a operacao da conta destino: \n");
                            operacao = sc.nextInt();
                            System.out.println("Digite o numero da conta destino: \n");
                            numero = sc.nextInt();
                            System.out.println("Digite um valor para transferir: \n");
                            valor = sc.nextDouble();
                            banco.transfereDinheiro(cpf, senha, agencia, operacao, numero, valor);
                            System.out.println("Transferencia efetuada com sucesso!");
                            break;
                        case 4:
                            System.out.println(banco.extratoBancario(cpf, senha));
                            break;
                        case 5:
                            System.out.println("Cheque especial!");
                            System.out.println(banco.sacaChequeEspecial(cpf, senha));
                            break;
                        case 6:
                            banco.pagaChequeEspecial(cpf, senha);
                            System.out.println("Dando baixar no chegue especial!");
                            break;
                        case 7:
                            banco.deletaConta(cpf, senha);
                            senha = cpf = "";
                            System.out.println("Conta excluida com sucesso!");
                            break;
                        case 0:
                            senha = cpf = "";
                            break;
                        default:
                            System.out.println("Opcao invalida!");

                    }
                } else {
                    System.out.println("Bem vindo ao nosso sistema\n" +
                            "Comece escolhendo uma das nossas operacoes\n");
                    System.out.println("1 - Acessar conta\n" +
                            "2 - Criar conta\n" +
                            "3 - Cancelar conta\n");
                    opcao = sc.nextInt();
                    sc.nextLine();
                    switch (opcao) {
                        case 1:
                            System.out.println("Digite sua credenciais para acessar sua conta:\nDigite o cpf: ");
                            cpf = sc.nextLine();
                            System.out.println("Digite sua senha: ");
                            senha = sc.nextLine();
                            if (banco.acessarConta(cpf, senha)) {
                                System.out.println("Acesos autorizado!");
                            } else {
                                System.out.println("Conta inexistente ou credenciais estao erradas!");
                                cpf = "";
                                senha = "";
                            }
                            break;
                        case 2:

                            System.out.println("Qual tipo de conta deseja criar: \n" +
                                    "1 - Conta corrente\n" +
                                    "2 - Conta Poupanca\n");
                            opcao = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Agora digite seus dados conta:\nDigite seu nome: ");
                            String nome = sc.nextLine();
                            System.out.println("Digite sua idade: ex - 30/12/1990");
                            String idade = sc.nextLine();
                            sc.nextLine();
                            System.out.println("Digite seu sexo: ex - masculino ou feminino");
                            String sexo = sc.nextLine();
                            System.out.println("Digite seu cpf: ");
                            String novoCpf = sc.nextLine();
                            System.out.println("Digite sua senha: ");
                            String novaSenha = sc.nextLine();


                            if (opcao == 1) {
                                banco.criaContaCorrente(nome, sexo, LocalDate.parse(idade, DateTimeFormatter.ofPattern("dd/MM/yyyy")), novoCpf, novaSenha);
                            } else if (opcao == 2) {
                                banco.criaContaPoupanca(nome, sexo, LocalDate.parse(idade, DateTimeFormatter.ofPattern("dd/MM/yyyy")), novoCpf, novaSenha);
                            }
                            System.out.println("Parabens conta criada com sucesso!");
                            break;

                        case 3:
                            System.out.println("Digite sua credenciais para excluir a conta:\nDigite o cpf: ");
                            novoCpf = sc.nextLine();
                            System.out.println("Digite a senha: ");
                            novaSenha = sc.nextLine();
                            banco.deletaConta(novoCpf, novaSenha);
                            System.out.println("Conta excluida com sucesso!");
                            break;
                        default:
                            System.out.println("Opcao invalida!");
                    }
                }
            }
        }catch (DadosInvalidosExcepiton e){
            e.printStackTrace();
        }catch (SaldoInvalidoException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("\nImpossivel recuperar o sistema!");
        }
    }
}