package APS_2.APS_2_ArqObj.Cartao;


import APS_2.APS_2_ArqObj.Autenticacao.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/APS_2/APS_2_ArqObj/ContaCorrente/Cartao")
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<CartaoDTO> listarCartoes() {
        List<CartaoDTO> cartoes = cartaoService.listaDeCartao()
            .stream()
            .map(c -> new CartaoDTO(
                    c.getNumeroCartao(),
                    c.getTipo(),
                    c.getStatus(),
                    c.getValidade(),
                    c.getContaCorrente().getCliente().getCPF()
            ))
            .toList();
        return cartoes;
}

@GetMapping("/{num}")
public CartaoDTO buscarCartaoPorNumero(@PathVariable String num) {
    var c = cartaoService.getCartao(num);
    CartaoDTO dto = new CartaoDTO(
            c.getNumeroCartao(),
            c.getTipo(),
            c.getStatus(),
            c.getValidade(),
            c.getContaCorrente().getCliente().getCPF()
    );
    return dto;
}

@PostMapping
public CartaoDTO criarCartao(
        @RequestHeader(name = "token") String token,
        @RequestBody CartaoDTO dto) {
    usuarioService.validarToken(token);
    var salvo = cartaoService.criaCartaoDTO(dto);
    CartaoDTO resposta = new CartaoDTO(
            salvo.getNumeroCartao(),
            salvo.getTipo(),
            salvo.getStatus(),
            salvo.getValidade(),
            salvo.getContaCorrente().getCliente().getCPF()
    );
    return resposta;
}

@PutMapping("/{num}")
public CartaoDTO cancelarCartao(
        @PathVariable String num,
        @RequestHeader(name = "token") String token) {
    usuarioService.validarToken(token);
    var cancelado = cartaoService.cancelamentoCartao(num);
    CartaoDTO dto = new CartaoDTO(
            cancelado.getNumeroCartao(),
            cancelado.getTipo(),
            cancelado.getStatus(),
            cancelado.getValidade(),
            cancelado.getContaCorrente().getCliente().getCPF()
    );
    return dto;
}

@GetMapping("/status/{num}")
public String status(@PathVariable String num) {
    return cartaoService.status(num);
}
}
