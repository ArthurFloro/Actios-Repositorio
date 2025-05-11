package service;

import dao.UsuarioDAO;
import model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioDAO usuarioDAO;

    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    // Método para salvar um novo usuário
    public Usuario salvarUsuario(Usuario usuario) {
        validarUsuario(usuario);

        // Verifica se já existe um usuário com o mesmo email
        Optional<Usuario> usuarioExistente = usuarioDAO.findByEmail(usuario.getEmail());
        if (usuarioExistente.isPresent()) {
            throw new IllegalArgumentException("Já existe um usuário com o email fornecido");
        }

        return usuarioDAO.save(usuario);
    }

    // Método para listar todos os usuários
    public List<Usuario> listarTodos() {
        return usuarioDAO.findAll();
    }

    // Método para buscar um usuário por ID
    public Optional<Usuario> buscarPorId(Integer id) {
        return usuarioDAO.findById(id);
    }

    // Método para deletar um usuário
    public void deletarUsuario(Integer id) {
        usuarioDAO.deleteById(id);
    }

    // Método para realizar ações administrativas (permitido apenas para administradores)
    public void realizarAcaoAdministrativa(Usuario usuario) {
        if (!usuario.getTipo().equals("admin")) {
            throw new IllegalArgumentException("Ação administrativa permitida apenas para administradores");
        }

        // Ação administrativa aqui, por exemplo:
        // atualizar registros, deletar eventos, etc.
    }

    // Validação de campos obrigatórios
    private void validarUsuario(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do usuário é obrigatório");
        }
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("O email do usuário é obrigatório");
        }
        if (usuario.getSenha() == null || usuario.getSenha().trim().isEmpty()) {
            throw new IllegalArgumentException("A senha do usuário é obrigatória");
        }
    }
}
