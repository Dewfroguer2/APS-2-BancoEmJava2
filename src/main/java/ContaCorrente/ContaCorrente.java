package ContaCorrente;

import Cartao.Cartao;
import Cliente.Cliente;

import java.util.ArrayList;

public class ContaCorrente {
    private String Agencia;
    private String Numero;
    private  Float Saldo;
    private  Float Limite;
    private Cliente Cliente;
    private ArrayList<Cartao> Cartoes = new ArrayList<>();
  //  private  ArrayList<Movimentacao> Movimentacaos = new ArrayList<>();

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

    public void setLimite(Float limite) { Limite = limite; }

    public String getNumero() { return Numero; }

    public void setNumero(String numero) { Numero = numero; }

    public Float getSaldo() { return Saldo; }

    public void setSaldo(Float saldo) { Saldo = saldo; }

    public Cliente getCliente() { return Cliente; }

    public void setCliente(Cliente cliente) { Cliente = cliente; }
}
