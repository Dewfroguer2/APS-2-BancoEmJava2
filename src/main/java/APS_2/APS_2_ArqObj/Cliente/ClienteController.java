package APS_2.APS_2_ArqObj.Cliente;

import APS_2.APS_2_ArqObj.Autenticacao.Usuario;
import APS_2.APS_2_ArqObj.Autenticacao.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/Cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Cliente> listaCliente(@RequestParam(name = "nome", required = false) String nome){
        return clienteService.listaClientes(nome);
    }

    @GetMapping("/{id}")
    public Cliente buscaPorCpf(@PathVariable Integer id) {
        return clienteService.buscaPorId(id);
    }

    @PostMapping
    public Cliente cadastraCliente(@RequestHeader(name = "token") String token, @RequestBody Cliente cliente) {
        usuarioService.validarToken(token);
        return clienteService.salvar(cliente);
    }

    @PutMapping("/{cpf}")
    public  Cliente atulizarCliente(@PathVariable Integer id, @RequestBody Cliente cliente, @RequestHeader(name = "token") String token ) {
        usuarioService.validarToken(token); // Válida se o usuário possui um token para poder continuar
       return clienteService.atualizar(id, cliente);
    }
}
