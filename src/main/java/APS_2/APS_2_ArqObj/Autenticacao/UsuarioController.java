package APS_2.APS_2_ArqObj.Autenticacao;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Tag(name = "Usuario", description = "Cadastro e autenticação de usuários")
@RestController
@RequestMapping("/Usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Cadastrar usuário")
    @PostMapping
    public Usuario cadatrarUsuario(@RequestBody Usuario usuario){
        return usuarioService.cadastrarUsuario(usuario);
    }

    @Operation(summary = "Lista usuários")
    @GetMapping
    public Collection<Usuario> listaUsuarios(){
        return usuarioService.listaUsuarios();
    }

    @Operation(summary = "Login e geração de token")
    @PostMapping("/login")
    public String login(@RequestBody Usuario usuario){
        return usuarioService.login(usuario);
    }
}
