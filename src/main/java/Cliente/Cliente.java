package Cliente;

import ContaCorrente.ContaCorrente;

import java.time.LocalDate;

public class Cliente {

    private String CPF;
    private  String Nome;
    private LocalDate DataNascimento;
    private Float Salario;
    //private ContaCorrente Conta;
    private String emailCriado;

    public Cliente(String cpf, String nome, LocalDate dataNascimento, Float salario){
        this.CPF = cpf;
        this.Nome = nome;
        this.DataNascimento = dataNascimento;
        this.Salario = salario;
    }

    public String getEmailCriado() { return emailCriado; }

    public void setEmailCriado(String emailCriado) { this.emailCriado = emailCriado; }

    public Float getSalario() { return Salario; }

    public void setSalario(Float salario) { Salario = salario; }

    public String getNome() { return Nome; }

    public void setNome(String nome) { Nome = nome; }

    public LocalDate getDataNascimento() { return DataNascimento; }

    public void setDataNascimento(LocalDate dataNascimento) { DataNascimento = dataNascimento; }

    public String getCPF() { return CPF; }

    public void setCPF(String CPF) { this.CPF = CPF; }

    //public ContaCorrente getConta() { return Conta; }

    //public void setConta(ContaCorrente conta) { Conta = conta; }




}
