package dao;

import model.Evento;
import model.Inscricao;
import model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InscricaoDAO extends JpaRepository<Inscricao, Integer> {
    boolean existsByUsuarioAndEvento(Usuario usuario, Evento evento);
    Optional<Inscricao> findByNumeroInscricao(String numero);
}
