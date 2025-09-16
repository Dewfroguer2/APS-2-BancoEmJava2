package APS_2.APS_2_ArqObj.ContaCorrente;

import APS_2.APS_2_ArqObj.Cartao.Cartao;
import APS_2.APS_2_ArqObj.Cliente.Cliente;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class ContaCorrente {
        private String agencia;
        private String numero;
        private Float saldo;
        private Float limite;

        private Cliente cliente;
        private ArrayList<Cartao> cartoes = new ArrayList<>();
        private final ArrayList<Movimentacao> movimentacoes = new ArrayList<>();


    public ContaCorrente(){}

    public ContaCorrente(String agencia, String numero, Float saldo, Float limite){
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = saldo;
        this.limite = limite;
    }

    public String getAgencia() { return agencia; }

    public void setAgencia(String age) { agencia = age; }

    public ArrayList<Cartao> getCartoes() { return cartoes; }

    public void setCartoes(ArrayList<Cartao> cartoes) { this.cartoes = cartoes; }

    public Float getLimite() { return limite; }


    public String getNumero() { return numero; }

    public void setNumero(String numero) { this.numero = numero; }

    public Float getSaldo() { return saldo; }

    public Cliente getCliente() { return cliente; }

    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public void deposito(Integer valor){ saldo += valor; movimentacoes.add( new Movimentacao("Deposito", valor, LocalDate.now())); }

    public void saque(Integer valor){ saldo -= valor; movimentacoes.add(new Movimentacao("Saque", valor, LocalDate.now()));}

    public Collection<Movimentacao> movimentacao(){ return movimentacoes; }
}
