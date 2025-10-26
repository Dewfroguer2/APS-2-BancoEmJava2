package APS_2.APS_2_ArqObj.ContaCorrente;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record MovimentacaoDTO(
        @Schema(description = "Tipo de de movimentação", example = "Saque") String tipo,
        @Schema(description = "Valor que foi movimentado", example = "200,00") Integer valor,
        @Schema(description = "Data que aconteceu a movimentação", example = "2025-10-10") LocalDate data) {
}
