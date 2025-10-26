package APS_2.APS_2_ArqObj.Cartao;

import APS_2.APS_2_ArqObj.ContaCorrente.ContaCorrente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "cartao")
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "numero_cartao")
    private String numeroCartao;

    @Column(nullable = false)
    private String tipo;

    private String status;

    @Column(nullable = false)
    private LocalDate validade;


    @ManyToOne
    @JoinColumn(name = "conta_id")
    private ContaCorrente contaCorrente;

    public Cartao(){}

    public Cartao(String numeroCartao, String tipo, String status, LocalDate validade){
        this.numeroCartao = numeroCartao;
        this.tipo = tipo;
        this.status = status;
        this.validade = validade;
    }

    public String getNumeroCartao() { return numeroCartao; }
    public void setNumeroCartao(String numeroCartao) { numeroCartao = numeroCartao; }

    public String getStatus() { return status; }
    public void setStatus(String status) { status = status; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { tipo = tipo; }

    public LocalDate getValidade() { return validade; }
    public void setValidade(LocalDate validade) { validade = validade; }

    public ContaCorrente getContaCorrente() { return contaCorrente; }
    public void setContaCorrente(ContaCorrente contaCorrente) { this.contaCorrente = contaCorrente; }
}