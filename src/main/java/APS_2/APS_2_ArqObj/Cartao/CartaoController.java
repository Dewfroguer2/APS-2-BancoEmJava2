package APS_2.APS_2_ArqObj.Cartao;


import APS_2.APS_2_ArqObj.Autenticacao.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/APS_2/APS_2_ArqObj/ContaCorrente/Cartao")
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public Collection<Cartao> listaCartao(){ return cartaoService.listaDeCartao(); }

    @GetMapping("/{num}")
    public Cartao cartaoPorNumero(@PathVariable String num) { return cartaoService.getCartao(num); }

    @PostMapping
    public Cartao criaCartao(@RequestHeader(name = "token") String token, @RequestBody Cartao cartao) {
        usuarioService.validarToken(token);
        return cartaoService.criaCartao(cartao);
    }

    @PutMapping("/{num}")
    public Cartao canclaCartao(@PathVariable String num, @RequestHeader(name = "token") String token){
        usuarioService.validarToken(token);
        return cartaoService.cancelamentoCartao(num);
    }

    @GetMapping("/status/{num}")
    public String status(@PathVariable String num) { return  cartaoService.status(num); }
}
