package APS_2.APS_2_ArqObj.ContaCorrente;

import APS_2.APS_2_ArqObj.Autenticacao.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Tag(name="Conta Corrente", description = "Operações pra conta corrente")
@RestController
@RequestMapping("/ContaCorrente")
public class ContaCorrenteController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ContaCorrenteService contaCorrenteService;

    @Operation(summary = "Listar contas", description = "Retorna lista de contas (DTO)")
    @GetMapping
    public ResponseEntity<List<ContaCorrenteDTO>> listConta(){
        List<ContaCorrenteDTO> contas = contaCorrenteService.listaConta();
        return ResponseEntity.ok(contas); }

    @Operation(summary = "Buscar conta por CPF do cliente")
    @GetMapping("/{cpf}")
    public ResponseEntity<ContaCorrenteDTO> buscarContaPorCliente(@PathVariable String cpf) {
        var conta = contaCorrenteService.buscaContaPorCliente(cpf);
        ContaCorrenteDTO dto = new ContaCorrenteDTO(
                conta.getAgencia(),
                conta.getNumero(),
                conta.getSaldo(),
                conta.getLimite(),
                conta.getCliente().getCPF()
        );
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Criar conta", description = "Cria conta vinculada ao CPF do cliente")
    @PostMapping
    @Transactional
    public ResponseEntity<ContaCorrenteDTO> postConta(@RequestBody ContaCorrenteDTO dto, @RequestHeader(name = "token") String token ){
        usuarioService.validarToken(token);
        var conta = contaCorrenteService.cadastraConta(dto);
        ContaCorrenteDTO resposta = new ContaCorrenteDTO(
                conta.getAgencia(),
                conta.getNumero(),
                conta.getSaldo(),
                conta.getLimite(),
                conta.getCliente().getCPF()
        );
        return ResponseEntity.ok(resposta);
    }

    @Operation(summary = "Atualizar conta por CPF")
    @PutMapping("/{cpf}")
    public ResponseEntity<String> putConta(@PathVariable String cpf, @RequestBody ContaCorrenteDTO dto, @RequestHeader(name = "token") String token ){
        usuarioService.validarToken(token);
        contaCorrenteService.atualizaConta(cpf, dto);
        return ResponseEntity.ok("Conta atualizada com sucesso!");
    }

    @Operation(summary = "Excluir conta por CPF")
    @DeleteMapping("/{cpf}")
    public ResponseEntity<String> deletConta(@PathVariable String cpf, @RequestHeader(name = "token") String token ) {
        usuarioService.validarToken(token);
        contaCorrenteService.excluiConta(cpf );
        return ResponseEntity.ok("Conta excluída com sucesso!");
    }

    @Operation(summary = "Depósito saldo na conta")
    @PutMapping("/{cpf}/Deposito")
    public ResponseEntity<String> deposito(@PathVariable String cpf, @RequestBody Integer valor,  @RequestHeader(name = "token") String token ){
        usuarioService.validarToken(token);
        contaCorrenteService.deposito(valor, cpf);
        return ResponseEntity.ok("Depósito realizado");
    }

    @Operation(summary = "Faz um saque")
    @PutMapping("/{cpf}/Saque")
    public ResponseEntity<String> saque(@PathVariable String cpf, @RequestBody Integer valor, @RequestHeader(name = "token") String token ){
        usuarioService.validarToken(token);
        contaCorrenteService.saque(valor, cpf);
        return ResponseEntity.ok("Depósito realizado");
    }

    @Operation(summary = "Listar movimentações de uma conta")
    @GetMapping("/{cpf}/Movimentacao")
    public Collection<Movimentacao> listaMovimentacao(@PathVariable String cpf) { return contaCorrenteService.listaDeMovimentacao(cpf); }
}