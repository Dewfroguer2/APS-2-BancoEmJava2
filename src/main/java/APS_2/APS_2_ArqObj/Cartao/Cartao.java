package APS_2.APS_2_ArqObj.Cartao;

import APS_2.APS_2_ArqObj.ContaCorrente.ContaCorrente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String NumeroCartao;
    @Column(nullable = false)
    private String Tipo;
    private String Status;
    @Column(nullable = false)
    private LocalDate Validade;

    @JsonIgnore
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
