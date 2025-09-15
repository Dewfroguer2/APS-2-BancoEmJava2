package ContaCorrente;

import Cliente.Cliente;
import Cliente.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ContaCorrenteService {

    private final Map<String, ContaCorrente> ListaDeContas = new HashMap<>();

    @Autowired
    private ClienteService clienteService;

    public ContaCorrente cadastraConta(ContaCorrente conta){
        Cliente cliente = clienteService.buscaPorCpf(conta.getCliente().getCPF());

        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado para esse CPF: " + conta.getCliente().getCPF());
        }

        conta.setCliente(cliente); // Troca o cliente na conta para evitar possiveis erros
        cliente.setConta(conta);  // Salva a conta no cliente

        ListaDeContas.put(cliente.getCPF(), conta);
        return  conta;
    }

    public List<contaDTO> listaConta(){
        return ListaDeContas.values().stream()
                .map(c -> new contaDTO(
                        c.getAgencia(),
                        c.getNumero(),
                        c.getCliente().getCPF()
                ))
                .toList();
    }

    public contaDTO buscaContaPorCliente(String cpf){
        ContaCorrente conta = ListaDeContas.get(cpf);
        if (conta == null){
            throw  new RuntimeException(("Conta não encontrada para o CPF" + cpf));
        }
        return new contaDTO(conta.getAgencia(), conta.getNumero(), conta.getCliente().getCPF());
    }

    public void excluiConta(String cpf){ ListaDeContas.remove(cpf); }

    public void atualizaConta(String cpf, ContaCorrente conta){ ListaDeContas.replace(cpf, conta); }

}
