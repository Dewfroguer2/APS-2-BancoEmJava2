package APS_2.APS_2_ArqObj.ContaCorrente;

import java.time.LocalDate;

public record MovimentacaoDTO(String tipo, Integer valor, LocalDate data) {
}
