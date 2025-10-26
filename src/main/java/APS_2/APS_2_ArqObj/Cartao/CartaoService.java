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
    public Cartao criaCartaoDTO(CartaoDTO dto) {
        if (dto == null || dto.clienteCPF() == null) {
            throw new RuntimeException("Dados do cartão inválidos.");
        }

        ContaCorrente conta = contaCorrenteService.buscaConta(dto.clienteCPF());
        if (conta == null) {
            throw new RuntimeException("Conta não encontrada para o CPF: " + dto.clienteCPF());
        }

        Cartao cartao = new Cartao();
        cartao.setNumeroCartao(dto.numerCartao());
        cartao.setTipo(dto.tipo());
        cartao.setStatus(dto.status());
        cartao.setValidade(dto.validade());
        cartao.setContaCorrente(conta);

        return cartaoRepository.save(cartao);
    }

    public List<Cartao> listaDeCartao() {
        return cartaoRepository.findAll();
    }

    public Cartao getCartao(String numeroCartao) {
        return cartaoRepository.findByNumeroCartao(numeroCartao);
    }

    @Transactional
    public Cartao cancelamentoCartao(String numero) {
        Cartao cartao = cartaoRepository.findByNumeroCartao(numero);
        if (cartao == null) {
            throw new RuntimeException("Cartão não encontrado.");
        }
        cartao.setStatus("Cancelado");
        return cartaoRepository.save(cartao);
    }

    public String status(String num) {
        Cartao c = cartaoRepository.findByNumeroCartao(num);
        if (c == null) throw new RuntimeException("Cartão não encontrado");
        return c.getStatus();
    }}
