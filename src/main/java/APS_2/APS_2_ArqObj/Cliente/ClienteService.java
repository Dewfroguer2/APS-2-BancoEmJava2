package APS_2.APS_2_ArqObj.Cliente;

import APS_2.APS_2_ArqObj.Autenticacao.Usuario;
import org.hibernate.jpa.event.internal.CallbackDefinitionResolverLegacyImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listaClientes(String nome){
        if (nome == null){
            return clienteRepository.findAll();
        } else {
            return clienteRepository.findByNome(nome);
        }
    }

    public Cliente buscaPorId(Integer id) {
        return clienteRepository.findById(id).get();
    }
    public Cliente salvar(Cliente cliente){
        return clienteRepository.save(cliente);
    }


    public Cliente atualizar(Integer id, ClienteDTO clienteAtualizado) {
        Cliente cliente = buscaPorId(id);

        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado com id: " + id);
        }

        if (clienteAtualizado.name() != null) {
            cliente.setNome(clienteAtualizado.name());
        }
        if (clienteAtualizado.date() != null) {
            cliente.setDataNascimento(clienteAtualizado.date());
        }
        // o CPF geralmente não se altera, mas se quiser permitir:
        if (clienteAtualizado.cpf() != null) {
            cliente.setCPF(clienteAtualizado.cpf());
        }

        return clienteRepository.save(cliente);
    }


}
