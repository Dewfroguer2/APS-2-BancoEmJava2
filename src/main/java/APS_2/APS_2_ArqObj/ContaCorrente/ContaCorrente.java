package APS_2.APS_2_ArqObj.ContaCorrente;

import APS_2.APS_2_ArqObj.Cartao.Cartao;
import APS_2.APS_2_ArqObj.Cliente.Cliente;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class ContaCorrente {
    private String Agencia;
    private String Numero;
    private  Float Saldo;
    private  Float Limite;

    @JsonIgnore
    private Cliente Cliente;
    private ArrayList<Cartao> Cartoes = new ArrayList<>();
    private  ArrayList<Movimentacao> Movimentacoes = new ArrayList<>();

    public ContaCorrente(){}

    public ContaCorrente(String agencia, String numero, Float saldo, Float limite){
        this.Agencia = agencia;
        this.Numero = numero;
        this.Saldo = saldo;
        this.Limite = limite;
    }

    public String getAgencia() { return Agencia; }

    public void setAgencia(String agencia) { Agencia = agencia; }

    public ArrayList<Cartao> getCartoes() { return Cartoes; }

    public void setCartoes(ArrayList<Cartao> cartoes) { Cartoes = cartoes; }

    public Float getLimite() { return Limite; }


    public String getNumero() { return Numero; }

    public void setNumero(String numero) { Numero = numero; }

    public Float getSaldo() { return Saldo; }

    public Cliente getCliente() { return Cliente; }

    public void setCliente(Cliente cliente) { Cliente = cliente; }

    public void deposito(Integer valor){ Saldo += valor; Movimentacoes.add( new Movimentacao("Deposito", valor, LocalDate.now())); }

    public void saque(Integer valor){ Saldo -= valor; Movimentacoes.add(new Movimentacao("Saque", valor, LocalDate.now()));}

    public Collection<Movimentacao> movimentacao(){ return Movimentacoes; }
}
