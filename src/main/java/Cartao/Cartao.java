package Cartao;

import ContaCorrente.ContaCorrente;

import java.lang.management.ThreadInfo;
import java.time.LocalDate;

public class Cartao {
    private String NumeroCartao;
    private String Tipo;
    private String Status;
    private LocalDate Validade;
    private ContaCorrente contaCorrente;

    public Cartao(){}

    public void Cartao(String numeroCartao, String tipo, String status, LocalDate validade){
        this.NumeroCartao = numeroCartao;
        this.Tipo = tipo;
        this.Status = status;
        this.Validade = validade;
    }

    public String getNumeroCartao() { return NumeroCartao; }

    public void setNumeroCartao(String numeroCartao) { NumeroCartao = numeroCartao; }

    public String getStatus() { return Status; }

    public void setStatus(String status) { Status = status; }

    public String getTipo() { return Tipo; }

    public void setTipo(String tipo) { Tipo = tipo; }

    public LocalDate getValidade() { return Validade; }

    public void setValidade(LocalDate validade) { Validade = validade; }

    public ContaCorrente getContaCorrente() { return contaCorrente; }

    public void setContaCorrente(ContaCorrente contaCorrente) { this.contaCorrente = contaCorrente; }
}
