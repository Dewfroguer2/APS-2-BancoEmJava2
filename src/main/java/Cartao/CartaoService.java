package Cartao;


import ContaCorrente.ContaCorrente;
import ContaCorrente.ContaCorrenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class CartaoService {


    private final Map<String, Cartao> listaCartao = new HashMap<>();

    @Autowired
    private ContaCorrenteService contaCorrenteService;

    public Cartao criaCartao(Cartao cartao){
        ContaCorrente conta = contaCorrenteService.buscaConta(cartao.getContaCorrente().getCliente().getCPF());

        cartao.setContaCorrente(conta); // evitar erros de
        listaCartao.put(cartao.getNumeroCartao() , cartao);
        return cartao;
    }

    public Cartao getCartao(String numeroCartao){ return listaCartao.get(numeroCartao); }

    public Collection<Cartao> listaDeCartao(){ return listaCartao.values(); }


    public Cartao cancelamentoCartao(String numero){
        Cartao cartao = listaCartao.get(numero);
        if (cartao == null){
            throw new RuntimeException("Cartão não encontrado, informe um número valido");
        }
        cartao.setStatus("Cancelado");
        return cartao;
    }

    public String status(String num){ return listaCartao.get(num).getStatus(); }
}