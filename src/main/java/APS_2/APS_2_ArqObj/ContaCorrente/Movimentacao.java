package APS_2.APS_2_ArqObj.ContaCorrente;

import java.time.LocalDate;

public record Movimentacao(String tipo, Integer valor, LocalDate data) {
}
