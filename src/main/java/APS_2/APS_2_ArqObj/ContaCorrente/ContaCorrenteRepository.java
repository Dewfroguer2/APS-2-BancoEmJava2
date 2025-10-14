package APS_2.APS_2_ArqObj.ContaCorrente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, Integer> {
    ContaCorrente findByCliente_Cpf(String cpf);
}
