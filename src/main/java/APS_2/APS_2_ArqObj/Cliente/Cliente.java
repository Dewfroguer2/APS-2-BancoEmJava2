package APS_2.APS_2_ArqObj.Cliente;

import APS_2.APS_2_ArqObj.ContaCorrente.ContaCorrente;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

public class Cliente {
    private String cpf;
    private String nome;
    private LocalDate dataNascimento;
    private Float salario;
    private String emailCriado;

    @JsonIgnore
    private ContaCorrente conta;

    public Cliente(){}

    public Cliente(String cpf, String nome, LocalDate dataNascimento, Float salario){
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.salario = salario;
    }

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
