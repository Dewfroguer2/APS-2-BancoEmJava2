package APS_2.APS_2_ArqObj.Cliente;

import APS_2.APS_2_ArqObj.Autenticacao.Usuario;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class ClienteService {

    private final Map<String, Cliente> clientes = new HashMap<>();
    private Long nextId = 1L;

    public Collection<Cliente> listaClientes(){
        return clientes.values();
    }

    public Cliente cadastrarCliente(Cliente cliente, Usuario usuario){
        if (cliente.getCPF() == null) {
            cliente.setCPF("AUTO_" + nextId);
        }
        cliente.setEmailCriado(usuario.getEmail());
        clientes.put(cliente.getCPF(), cliente);
        return cliente;
    }

    public Cliente buscaPorCpf(String cpf){
        return clientes.get(cpf);
    }

    public Cliente editaCliente(String cpf, Cliente cliente){
        return clientes.put(cpf, cliente);
    }
}
