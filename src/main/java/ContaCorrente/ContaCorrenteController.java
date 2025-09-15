package ContaCorrente;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/ContaCorrente")
public class ContaCorrenteController {

    private ContaCorrenteService contaCorrenteService;

    @GetMapping
    public Collection<contaDTO> listConta(){ return contaCorrenteService.listaConta(); }

    @GetMapping("/{cpf}")
    public contaDTO getConta(@PathVariable String cpf){
        return contaCorrenteService.buscaContaPorCliente(cpf);
    }

    @PostMapping
    public void postConta(@RequestBody ContaCorrente conta){ contaCorrenteService.cadastraConta(conta); }

    @PutMapping("/{cpf}")
    public void putConta(@PathVariable String cpf, @RequestBody ContaCorrente contaCorrente){
        contaCorrenteService.atualizaConta(cpf, contaCorrente);
    }

    @DeleteMapping("{cpf}")
    public void deletConta(@PathVariable String cpf) { contaCorrenteService.excluiConta(cpf );}
}
