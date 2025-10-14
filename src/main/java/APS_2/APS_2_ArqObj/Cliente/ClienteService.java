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

    public Cliente atualizar(Integer id, Cliente clienteAtualizado){
        Cliente cliente = buscaPorId(id);
        if (cliente != null){
            cliente.setNome(clienteAtualizado.getNome());
        }

        if (cliente != null){
            cliente.setSalario(clienteAtualizado.getSalario());
        }
        if (cliente != null){
            cliente.setDataNascimento((clienteAtualizado.getDataNascimento()));
        }
        return clienteRepository.save(cliente);
    }
}
