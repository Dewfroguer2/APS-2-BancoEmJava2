package APS_2.APS_2_ArqObj.ContaCorrente;

import APS_2.APS_2_ArqObj.Cliente.Cliente;
import APS_2.APS_2_ArqObj.Cliente.ClienteRepository;
import APS_2.APS_2_ArqObj.Cliente.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ContaCorrenteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContaCorrenteRepository contaCorrenteRepository;

    @Transactional
    public ContaCorrente cadastraConta(ContaCorrente conta){
        if (conta == null || conta.getCliente() == null || conta.getCliente().getCPF() == null) {
            throw new RuntimeException("Conta ou Cliente inválido para cadastro.");
        }

        // busca cliente existente por CPF
        Cliente cliente = clienteRepository.findByCpf(conta.getCliente().getCPF());
        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado para esse CPF: " + conta.getCliente().getCPF());
        }

        // liga as entidades e persiste a conta
        conta.setCliente(cliente);
        cliente.setConta(conta);

        ContaCorrente salvo = contaCorrenteRepository.save(conta);
        // como cascade está definido, cliente já ficará consistente se você persistir por cliente; aqui salvamos a conta.
        return salvo;
    }

    public List<ContaCorrente> listaConta(){
        return contaCorrenteRepository.findAll();
    }

    public ContaCorrente buscaContaPorCliente(String cpf){
        ContaCorrente conta = contaCorrenteRepository.findByCliente_Cpf(cpf);
        if (conta == null){
            throw new RuntimeException("Conta não encontrada para o CPF: " + cpf);
        }
        return conta;
    }

    public ContaCorrente buscaConta(String cpf){
        // mesmo comportamento que buscaContaPorCliente
        return buscaContaPorCliente(cpf);
    }

    @Transactional
    public void excluiConta(String cpf){
        ContaCorrente conta = contaCorrenteRepository.findByCliente_Cpf(cpf);
        if (conta != null) {
            contaCorrenteRepository.delete(conta);
        }
    }

    @Transactional
    public void atualizaConta(String cpf, ContaCorrente contaAtualizada){
        ContaCorrente existente = contaCorrenteRepository.findByCliente_Cpf(cpf);
        if (existente == null) {
            throw new RuntimeException("Conta não encontrada para o CPF: " + cpf);
        }
        // atualiza campos permitidos (mantive apenas os campos existentes no seu modelo)
        if (contaAtualizada.getAgencia() != null) existente.setAgencia(contaAtualizada.getAgencia());
        if (contaAtualizada.getNumero() != null) existente.setNumero(contaAtualizada.getNumero());
        if (contaAtualizada.getSaldo() != null) {
            // se quiser, em vez de set, poderia somar ou validar; mantive simples
            // existente.setSaldo(contaAtualizada.getSaldo());
            // se o seu fluxo quiser preservar movimentações, prefira métodos de negócio (deposito/saque)
            existente.setCliente(contaAtualizada.getCliente() != null ? contaAtualizada.getCliente() : existente.getCliente());
        }
        contaCorrenteRepository.save(existente);
    }

    @Transactional
    public void deposito(Integer valor, String cpf){
        ContaCorrente conta = contaCorrenteRepository.findByCliente_Cpf(cpf);
        if (conta == null) throw new RuntimeException("Conta não encontrada para o CPF: " + cpf);
        conta.deposito(valor);
        contaCorrenteRepository.save(conta);
    }

    @Transactional
    public void saque(Integer valor, String cpf){
        ContaCorrente conta = contaCorrenteRepository.findByCliente_Cpf(cpf);
        if (conta == null) throw new RuntimeException("Conta não encontrada para o CPF: " + cpf);
        Float limite = conta.getLimite() == null ? 0f : conta.getLimite();
        Float saldo = conta.getSaldo() == null ? 0f : conta.getSaldo();
        if (saldo + limite < valor) {
            throw new RuntimeException("Saldo insuficiente!");
        }
        conta.saque(valor);
        contaCorrenteRepository.save(conta);
    }

    public Collection<Movimentacao> listaDeMovimentacao(String cpf){
        ContaCorrente conta = contaCorrenteRepository.findByCliente_Cpf(cpf);
        if (conta == null) throw new RuntimeException("Conta não encontrada para o CPF: " + cpf);
        return conta.movimentacao();
    }
}

