package APS_2.APS_2_ArqObj.ContaCorrente;

import APS_2.APS_2_ArqObj.Autenticacao.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/APS_2/APS_2_ArqObj/ContaCorrente")
public class ContaCorrenteController {

    private UsuarioService usuarioService;

    private ContaCorrenteService contaCorrenteService;

    @GetMapping
    public Collection<contaDTO> listConta(){ return contaCorrenteService.listaConta(); }

    @GetMapping("/{cpf}")
    public contaDTO getConta(@PathVariable String cpf){
        return contaCorrenteService.buscaContaPorCliente(cpf);
    }

    @PostMapping
    public void postConta(@RequestBody ContaCorrente conta, @RequestHeader(name = "token") String token ){
        usuarioService.validarToken(token); // Válida se o usuário possui um token para poder continuar
        contaCorrenteService.cadastraConta(conta); }

    @PutMapping("/{cpf}")
    public void putConta(@PathVariable String cpf, @RequestBody ContaCorrente contaCorrente, @RequestHeader(name = "token") String token ){
        usuarioService.validarToken(token); // Válida se o usuário possui um token para poder continuar
        contaCorrenteService.atualizaConta(cpf, contaCorrente);
    }

    @DeleteMapping("/{cpf}")
    public void deletConta(@PathVariable String cpf, @RequestHeader(name = "token") String token ) {
        usuarioService.validarToken(token); // Válida se o usuário possui um token para poder continuar
        contaCorrenteService.excluiConta(cpf );
    }

    @PutMapping("/{cpf}/deposito")
    public void deposito(@PathVariable String cpf, @RequestBody Integer valor,  @RequestHeader(name = "token") String token ){
        usuarioService.validarToken(token); // Válida se o usuário possui um token para poder continuar
        contaCorrenteService.deposito(valor, cpf);
    }

    @PutMapping("/{cpf}/saque")
    public void saque(@PathVariable String cpf, @RequestBody Integer valor, @RequestHeader(name = "token") String token ){
        usuarioService.validarToken(token); // Válida se o usuário possui um token para poder continuar
        contaCorrenteService.saque(valor, cpf);
    }

    @GetMapping("/{cpf}/Movimentacao")
    public Collection<Movimentacao> listaMovimentacao(@PathVariable String cpf) { return contaCorrenteService.listaDeMovimentacao(cpf); }
}
