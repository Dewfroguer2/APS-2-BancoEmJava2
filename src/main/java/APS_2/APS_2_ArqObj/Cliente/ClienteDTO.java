package APS_2.APS_2_ArqObj.Cliente;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ClienteDTO(
        @Schema(description = "Nome do cliente", example = "Wesley Lima")@NotBlank String name,
        @Schema(description = "CPF do cliente", example = "999.999.999-99")@NotBlank String cpf,
        @Schema(description = "Data de nascimento do cliente", example = "2002-10-10")@NotNull LocalDate date) {
}
