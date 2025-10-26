package APS_2.APS_2_ArqObj.Cartao;

import java.time.LocalDate;

public record CartaoDTO(String numerCartao, String tipo, String status, LocalDate validade, String clienteCPF) {
}
