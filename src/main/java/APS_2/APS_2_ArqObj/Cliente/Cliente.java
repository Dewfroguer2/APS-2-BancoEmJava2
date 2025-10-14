package APS_2.APS_2_ArqObj.Cliente;

import APS_2.APS_2_ArqObj.ContaCorrente.ContaCorrente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String cpf;
    @Column(nullable = false)
    private String nome;

    private LocalDate dataNascimento;
    private Float salario;
    private String emailCriado;

    @JsonIgnore
    @OneToOne(mappedBy = "cliente")
    private ContaCorrente conta;

    public Integer getId(){ return id;}

    public void setId(Integer id){ this.id = id;}

    public String getEmailCriado() { return emailCriado; }

    public void setEmailCriado(String emailCriado) { this.emailCriado = emailCriado; }

    public Float getSalario() { return salario; }

    public void setSalario(Float salario) { this.salario = salario; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getDataNascimento() { return dataNascimento; }

    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getCPF() { return cpf; }

    public void setCPF(String CPF) { this.cpf = CPF; }

    public ContaCorrente getConta() { return conta; }

    public void setConta(ContaCorrente conta) { this.conta = conta; }




}
