package Cartao;


import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/ContaCorrente/Cartao")
public class CartaoController {

    private CartaoService cartaoService;
    @GetMapping
    public Collection<Cartao> listaCartao(){ return cartaoService.listaDeCartao(); }

    @GetMapping("/{num}")
    public Cartao cartaoPorNumero(@PathVariable String num) { return cartaoService.getCartao(num); }

    @PostMapping
    public Cartao criaCartao(@RequestBody Cartao cartao){ return cartaoService.criaCartao(cartao); }

    @PutMapping("/{num}")
    public Cartao canclaCartao(@PathVariable String num){ return cartaoService.cancelamentoCartao(num); }

    @GetMapping("/status/{num}")
    public String status(@PathVariable String num) { return  cartaoService.status(num); }
}
