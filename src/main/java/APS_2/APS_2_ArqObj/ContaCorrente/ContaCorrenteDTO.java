package APS_2.APS_2_ArqObj.ContaCorrente;

import io.swagger.v3.oas.annotations.media.Schema;

public record ContaCorrenteDTO(
        @Schema(description = "Número da agencia", example = "99999-99") String agencia,
        @Schema(description = "Número da conta", example = "9999999") String numero,
        @Schema(description = "Saldo atual da conta", example = "999.99") Float saldo,
        @Schema(description = "Limite da conta", example = "1000.00") Float limite,
        @Schema(description = "CPF do cliente da conta corrente", example = "999-999-999-99") String clienteCpf) {
}