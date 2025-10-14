package APS_2.APS_2_ArqObj.ContaCorrente;

import APS_2.APS_2_ArqObj.Cartao.Cartao;
import APS_2.APS_2_ArqObj.Cliente.Cliente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "conta_corrente")
public class ContaCorrente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String agencia;

    @Column(nullable = false)
    private String numero;

    @Column
    private Float saldo;

    @Column
    private Float limite;

    @OneToOne
    @JoinColumn(name = "cliente_id", unique = true)
    private Cliente cliente;

    @OneToMany(mappedBy = "contaCorrente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cartao> cartoes = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "movimentacao", joinColumns = @JoinColumn(name = "conta_id"))
    private List<Movimentacao> movimentacoes = new ArrayList<>();


    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getAgencia() { return agencia; }
    public void setAgencia(String age) { this.agencia = age; }

    public List<Cartao> getCartoes() { return cartoes; }
    public void setCartoes(List<Cartao> cartoes) { this.cartoes = cartoes; }

    public Float getLimite() { return limite; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public Float getSaldo() { return saldo; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public void deposito(Integer valor){
        if (this.saldo == null) this.saldo = 0f;
        this.saldo += valor;
        this.movimentacoes.add(new Movimentacao("Deposito", valor, LocalDate.now()));
    }

    public void saque(Integer valor){
        if (this.saldo == null) this.saldo = 0f;
        this.saldo -= valor;
        this.movimentacoes.add(new Movimentacao("Saque", valor, LocalDate.now()));
    }


    public java.util.Collection<Movimentacao> movimentacao(){ return movimentacoes; }
}
