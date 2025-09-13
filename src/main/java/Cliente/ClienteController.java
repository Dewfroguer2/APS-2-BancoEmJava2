package Cliente;

import Autenticacao.Usuario;
import Autenticacao.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/Cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public Collection<Cliente> listaCliente(){
        return clienteService.listaClientes();
    }

    @GetMapping("/{cpf}")
    public Cliente buscaPorCpf(@PathVariable String cpf) {
        return clienteService.buscaPorCpf(cpf);
    }

    @PostMapping
    public Cliente cadastraCliente(@RequestHeader(name = "token") String token, @RequestBody Cliente cliente) {
        Usuario usuario = usuarioService.validarToken(token);
        return clienteService.cadastrarCliente(cliente, usuario);
    }

    @PutMapping("/{cpf}")
    public  Cliente atulizarCliente(@PathVariable String cpf, @RequestBody Cliente cliente) {
       return clienteService.editaCliente(cpf, cliente);
    }
}
