package service;

import dao.InscricaoDAO;
import model.Evento;
import model.Inscricao;
import model.Usuario;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class InscricaoService {
    private final InscricaoDAO inscricaoDAO;
    private final EventoService eventoService;

    public InscricaoService(InscricaoDAO inscricaoDAO, EventoService eventoService) {
        this.inscricaoDAO = inscricaoDAO;
        this.eventoService = eventoService;
    }

    public Optional<Inscricao> inscrever(Usuario usuario, Evento evento) {
        if (eventoService.isEventoPassado(evento)) {
            return Optional.empty();
        }
        if (inscricaoDAO.existsByUsuarioAndEvento(usuario, evento)) {
            return Optional.empty();
        }

        Inscricao inscricao = new Inscricao();
        inscricao.setUsuario(usuario);
        inscricao.setEvento(evento);
        inscricao.setNumeroInscricao(UUID.randomUUID().toString());

        return Optional.of(inscricaoDAO.save(inscricao));
    }
}

