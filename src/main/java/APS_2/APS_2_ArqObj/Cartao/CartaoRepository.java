package APS_2.APS_2_ArqObj.Cartao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, Integer> {
    Cartao findByNumeroCartao(String numeroCartao);
}
