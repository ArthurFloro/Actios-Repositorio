package service;

import dao.EventoDAO;
import dao.CategoriaDAO;
import dao.FaculdadeDAO;
import model.Categoria;
import model.Evento;
import model.Faculdade;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    private final EventoDAO eventoDAO;
    private final CategoriaDAO categoriaDAO;
    private final FaculdadeDAO faculdadeDAO;

    public EventoService(EventoDAO eventoDAO, CategoriaDAO categoriaDAO, FaculdadeDAO faculdadeDAO) {
        this.eventoDAO = eventoDAO;
        this.categoriaDAO = categoriaDAO;
        this.faculdadeDAO = faculdadeDAO;
    }

    public Evento salvarEvento(Evento evento) {
        validarEvento(evento);

        // Validações para Categoria e Faculdade
        Categoria categoria = categoriaDAO.findById(evento.getCategoria().getIdCategoria())
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));

        Faculdade faculdade = faculdadeDAO.findById(evento.getFaculdade().getIdFaculdade())
                .orElseThrow(() -> new IllegalArgumentException("Faculdade não encontrada"));

        evento.setCategoria(categoria);
        evento.setFaculdade(faculdade);

        return eventoDAO.save(evento);
    }

    public boolean isEventoPassado(Evento evento) {
        return evento.getDataFim().isBefore(LocalDateTime.now());
    }

    public List<Evento> listarEventosAtivos() {
        return eventoDAO.findAll()
                .stream()
                .filter(evento -> evento.getDataFim().isAfter(LocalDateTime.now()))
                .toList();
    }

    public List<Evento> listarTodos() {
        return eventoDAO.findAll();
    }

    public Optional<Evento> buscarPorId(Integer id) {
        return eventoDAO.findById(id);
    }

    public void deletarEvento(Integer id) {
        eventoDAO.deleteById(id);
    }

    private void validarEvento(Evento evento) {
        // Validação de campos obrigatórios
        if (evento.getTitulo() == null || evento.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("O título do evento é obrigatório");
        }
        if (evento.getDataInicio() == null || evento.getDataFim() == null) {
            throw new IllegalArgumentException("As datas de início e fim são obrigatórias");
        }

        // Verificação se a data de fim é posterior à data de início
        if (evento.getDataFim().isBefore(evento.getDataInicio())) {
            throw new IllegalArgumentException("A data de fim não pode ser anterior à data de início");
        }

        // Proibição de eventos com data de início no passado
        if (evento.getDataInicio().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Não é possível criar eventos com data de início no passado");
        }
    }
}