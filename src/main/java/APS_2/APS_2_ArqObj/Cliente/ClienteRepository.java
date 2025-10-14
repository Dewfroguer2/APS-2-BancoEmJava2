package APS_2.APS_2_ArqObj.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    public Cliente findByCpf(String cpf);
    public List<Cliente> findByNome(String nome);
    public List<Cliente> finByNomeAndEmail(String nome, String emailCriado);
}
