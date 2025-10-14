package APS_2.APS_2_ArqObj.Autenticacao;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class UsuarioService {

    // tokens temporários em memória (token -> Usuario)
    private final Map<String, Usuario> tokens = new HashMap<>();

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario cadastrarUsuario(Usuario usuario){
        if (usuario == null || usuario.getEmail() == null || usuario.getPassowrd() == null) {
            throw new RuntimeException("Dados inválidos para cadastro");
        }

        // hash da senha antes de salvar
        String raw = usuario.getPassowrd();
        String hashed = BCrypt.hashpw(raw, BCrypt.gensalt());
        usuario.setPassowrd(hashed);

        return usuarioRepository.save(usuario);
    }

    public Collection<Usuario> listaUsuarios(){
        return usuarioRepository.findAll();
    }

    public String login(Usuario usuario){
        if (usuario == null || usuario.getEmail() == null || usuario.getPassowrd() == null) {
            throw new RuntimeException("Dados de login inválidos");
        }

        Optional<Usuario> opt = usuarioRepository.findById(usuario.getEmail());
        if (opt.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");
        }

        Usuario stored = opt.get();
        if (BCrypt.checkpw(usuario.getPassowrd(), stored.getPassowrd())) {
            String token = UUID.randomUUID().toString();
            tokens.put(token, stored);
            return token;
        }

        throw new RuntimeException("Credenciais inválidas");
    }

    public Usuario validarToken(String token) {
        Usuario usuario = tokens.get(token);
        if (usuario == null) {
            throw new RuntimeException("Token invalido");
        }
        return usuario;
    }

    public void invalidateToken(String token) {
        tokens.remove(token);
    }
}