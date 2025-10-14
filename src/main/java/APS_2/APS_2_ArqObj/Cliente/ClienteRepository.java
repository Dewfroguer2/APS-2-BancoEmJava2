package APS_2.APS_2_ArqObj.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Cliente findByCpf(String cpf);
    List<Cliente> findByNome(String nome);
    List<Cliente> findByNomeAndEmailCriado(String nome, String emailCriado);
}
