package APS_2.APS_2_ArqObj.Cartao;


import APS_2.APS_2_ArqObj.ContaCorrente.ContaCorrente;
import APS_2.APS_2_ArqObj.ContaCorrente.ContaCorrenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ContaCorrenteService contaCorrenteService;

    @Transactional
    public Cartao criaCartao(Cartao cartao){
        if (cartao == null || cartao.getContaCorrente() == null || cartao.getContaCorrente().getCliente() == null) {
            throw new RuntimeException("Dados do cartão inválidos.");
        }

        String cpf = cartao.getContaCorrente().getCliente().getCPF();
        ContaCorrente conta = contaCorrenteService.buscaConta(cpf);
        if (conta == null) {
            throw new RuntimeException("Conta não encontrada para o CPF: " + cpf);
        }

        cartao.setContaCorrente(conta);
        Cartao salvo = cartaoRepository.save(cartao);
        return salvo;
    }

    public Cartao getCartao(String numeroCartao){
        return cartaoRepository.findByNumeroCartao(numeroCartao);
    }

    public Collection<Cartao> listaDeCartao(){
        List<Cartao> all = cartaoRepository.findAll();
        return all;
    }

    @Transactional
    public Cartao cancelamentoCartao(String numero){
        Cartao cartao = cartaoRepository.findByNumeroCartao(numero);
        if (cartao == null){
            throw new RuntimeException("Cartão não encontrado, informe um número válido");
        }
        cartao.setStatus("Cancelado");
        return cartaoRepository.save(cartao);
    }

    public String status(String num){
        Cartao c = cartaoRepository.findByNumeroCartao(num);
        if (c == null) throw new RuntimeException("Cartão não encontrado");
        return c.getStatus();
    }
}
