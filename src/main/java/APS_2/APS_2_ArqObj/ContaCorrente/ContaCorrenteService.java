package APS_2.APS_2_ArqObj.ContaCorrente;

import APS_2.APS_2_ArqObj.Cliente.Cliente;
import APS_2.APS_2_ArqObj.Cliente.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            throw  new RuntimeException("Conta não encontrada para o CPF" + cpf);
        }
        return new contaDTO(conta.getAgencia(), conta.getNumero(), conta.getCliente().getCPF());
    }

    public ContaCorrente buscaConta(String cpf){
        ContaCorrente conta = ListaDeContas.get(cpf);
        if (conta == null){
            throw new RuntimeException("Conta não encontrada para o CPF" + cpf);
        }
        return conta;
    }

    public void excluiConta(String cpf){ ListaDeContas.remove(cpf); }

    public void atualizaConta(String cpf, ContaCorrente conta){ ListaDeContas.replace(cpf, conta); }

    public void deposito(Integer valor, String cpf){ ListaDeContas.get(cpf).deposito(valor); }

    public void saque(Integer valor, String cpf){
        ContaCorrente conta = ListaDeContas.get(cpf);
        if(conta.getSaldo() + conta.getLimite() < valor){
            throw new RuntimeException("Saldo insuficiente!");
        }
        conta.saque(valor);
    }


    public Collection<Movimentacao> listaDeMovimentacao(String cpf){ return ListaDeContas.get(cpf).movimentacao(); }
}
