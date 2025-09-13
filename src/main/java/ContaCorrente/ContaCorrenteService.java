package ContaCorrente;

import Cliente.Cliente;
import Cliente.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ContaCorrenteService {

    private final Map<String, ContaCorrente> listaDeContas = new HashMap<>();

    @Autowired
    private ClienteService clienteService;

    public ContaCorrente cadastraConta(ContaCorrente conta){
        Cliente cliente = clienteService.buscaPorCpf(conta.getCliente().getCPF());

        listaDeContas.put(conta.);
    }

}
