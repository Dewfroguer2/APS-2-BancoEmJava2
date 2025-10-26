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
    public ContaCorrente cadastraConta(ContaCorrenteDTO dto){
        if (dto == null || dto.clienteCpf() == null) {
            throw new RuntimeException("Conta ou Cliente inválido para cadastro.");
        }

        // busca cliente existente por CPF
        Cliente cliente = clienteRepository.findByCpf(dto.clienteCpf());
        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado para esse CPF: " + dto.clienteCpf());
        }

        ContaCorrente conta = new ContaCorrente();
        conta.setAgencia(dto.agencia());
        conta.setNumero(dto.numero());
        conta.setCliente(cliente);
        cliente.setConta(conta);


        return contaCorrenteRepository.save(conta);
    }

    public List<ContaCorrenteDTO> listaConta(){
        return contaCorrenteRepository.findAll()
                .stream()
                .map(c -> new ContaCorrenteDTO(
                        c.getAgencia(),
                        c.getNumero(),
                        c.getSaldo(),
                        c.getLimite(),
                        c.getCliente().getCPF()
                ))
                .toList();
    }

    public ContaCorrente buscaContaPorCliente(String cpf){
        ContaCorrente conta = contaCorrenteRepository.findByCliente_Cpf(cpf);
        if (conta == null){
            throw new RuntimeException("Conta não encontrada para o CPF: " + cpf);
        }
        return conta;
    }

    public ContaCorrente buscaConta(String cpf){
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
    public void atualizaConta(String cpf, ContaCorrenteDTO dto){
        ContaCorrente existente = contaCorrenteRepository.findByCliente_Cpf(cpf);
        if (existente == null) {
            throw new RuntimeException("Conta não encontrada para o CPF: " + cpf);
        }
        if (dto.agencia() != null) existente.setAgencia(dto.agencia());
        if (dto.numero() != null) existente.setNumero(dto.numero());

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

