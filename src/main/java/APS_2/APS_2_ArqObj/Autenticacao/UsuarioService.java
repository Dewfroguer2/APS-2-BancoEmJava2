package APS_2.APS_2_ArqObj.Autenticacao;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

@Service
public class UsuarioService {

    private HashMap<String, Usuario> usuarios = new HashMap<>();
    private HashMap<String, Usuario> tokens = new HashMap<>();

    public Usuario cadastrarUsuario(Usuario usuario){
        String passoword = usuario.getPassowrd();

        usuario.setPassowrd(BCrypt.hashpw(passoword, BCrypt.gensalt()));
        usuarios.put(usuario.getEmail(),usuario);
        return usuario;

    }

    public Collection<Usuario> listaUsuarios(){
        return usuarios.values();
    }

    public String login(Usuario usuario){
        Usuario user = usuarios.get(usuario.getEmail());
        if (BCrypt.checkpw(usuario.getPassowrd(), user.getPassowrd())) {
            String token = UUID.randomUUID().toString();
            tokens.put(token, usuario);
            return token;
        }
        throw new RuntimeException("Usuário não encontrado");
    }

    public Usuario validarToken(String token) {
        Usuario usuario = tokens.get(token);

        if (usuario == null) {
            throw new RuntimeException(("Token invalido"));
        }

        return usuario;
    }
}
