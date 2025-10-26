package APS_2.APS_2_ArqObj.Cliente;

import APS_2.APS_2_ArqObj.Autenticacao.Usuario;
import APS_2.APS_2_ArqObj.Autenticacao.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Tag(name = "Cliente", description = "Operações sobre clientes")
@RestController
@RequestMapping("/Cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Listar clientes", description = "Retorna a lista de clientes em formato DTO")
    @GetMapping
    public List<ClienteDTO> listaCliente(@RequestParam(name = "nome", required = false) String nome){
        List<Cliente> clientes = clienteService.listaClientes(nome);

        // converte entidades para DTOs
        List<ClienteDTO> clientesDto = clientes.stream()
                .map(c -> new ClienteDTO(c.getNome(), c.getCPF(), c.getDataNascimento()))
                .toList();

        return clientesDto;
    }

    @Operation(summary = "Buscar cliente por id", description = "Retorna um cliente (DTO) pelo id")
    @GetMapping("/{id}")
    public ClienteDTO buscaPorCpf(@PathVariable Integer id) {
        Cliente cliente = clienteService.buscaPorId(id);

        ClienteDTO clienteDto = new ClienteDTO(
                cliente.getNome(),
                cliente.getCPF(),
                cliente.getDataNascimento()
        );

        return clienteDto;
    }

    @Operation(summary = "Criar cliente", description = "Cria um cliente a partir do ClienteDTO")
    @PostMapping
    public ClienteDTO cadastraCliente(@RequestHeader(name = "token") String token, @RequestBody ClienteDTO clienteDTO) {
        usuarioService.validarToken(token);
        Cliente cliente = new Cliente();
        cliente.setCPF(clienteDTO.cpf());
        cliente.setNome(clienteDTO.name());
        cliente.setDataNascimento(clienteDTO.date());
        clienteService.salvar(cliente);

        return clienteDTO;
    }

    @Operation(summary = "Atualizar cliente", description = "Atualiza um cliente pelo id com os dados do DTO")
    @PutMapping("/{cpf}")
    public  ClienteDTO atulizarCliente(@PathVariable Integer id, @RequestBody ClienteDTO clienteDto, @RequestHeader(name = "token") String token ) {
        usuarioService.validarToken(token); // Válida se o usuário possui um token para poder continuar
        Cliente atualizado = clienteService.atualizar(id, clienteDto);
        return clienteDto;
    }
}
