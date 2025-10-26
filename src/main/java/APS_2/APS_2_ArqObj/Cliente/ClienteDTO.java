package APS_2.APS_2_ArqObj.Cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ClienteDTO(@NotBlank String name, @NotBlank String cpf, @NotNull LocalDate date) {
}
