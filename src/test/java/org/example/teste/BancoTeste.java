package org.example.teste;

import org.example.model.Banco;
import org.example.model.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class BancoTeste {

    @Test
    public void testeCpfvalidacao(){
        Banco banco = new Banco();

        banco.criaContaCorrente("marcos","MASCULINO", LocalDate.of(1995,8,16),"42473894752","123");
        banco.criaContaCorrente("ana","FEMININO", LocalDate.of(1995,8,16),"07251370379","123");
        banco.depositarDinheiro("42473894752","123",250.0);
        System.out.println(banco.extratoBancario("42473894752","123"));
        banco.deletaConta("42473894752","123");
        //Agencia: 4285
        //Operacao: 13
        //Numero: 2

    }
}
