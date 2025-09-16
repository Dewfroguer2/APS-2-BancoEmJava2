package APS_2.APS_2_ArqObj.Autenticacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/Usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public Usuario cadatrarUsuario(@RequestBody Usuario usuario){
        return usuarioService.cadastrarUsuario(usuario);
    }

    @GetMapping
    public Collection<Usuario> listaUsuarios(){
        return usuarioService.listaUsuarios();
    }

    @PostMapping("/login")
    public String login(@RequestBody Usuario usuario){
        return usuarioService.login(usuario);
    }
}
