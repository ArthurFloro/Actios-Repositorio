package controller;

import model.Evento;
import service.EventoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {
    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping("/ativos")
    public List<Evento> listarAtivos() {
        return eventoService.listarEventosAtivos();
    }
}
