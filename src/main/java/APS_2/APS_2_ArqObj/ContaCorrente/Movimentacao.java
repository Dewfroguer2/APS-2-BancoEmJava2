package APS_2.APS_2_ArqObj.ContaCorrente;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;

@Embeddable
public record Movimentacao(String tipo, Integer valor, LocalDate data) { }
