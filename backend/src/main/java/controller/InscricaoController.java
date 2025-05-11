package controller;

import model.Inscricao;
import model.Usuario;
import model.Evento;
import service.InscricaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/inscricoes")
public class InscricaoController {
    private final InscricaoService inscricaoService;

    public InscricaoController(InscricaoService inscricaoService) {
        this.inscricaoService = inscricaoService;
    }

    @PostMapping
    public ResponseEntity<?> inscrever(@RequestBody InscricaoRequest request) {
        Usuario usuario = request.getUsuario();
        Evento evento = request.getEvento();

        Optional<Inscricao> inscricao = inscricaoService.inscrever(usuario, evento);

        return inscricao.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().body("Inscrição inválida ou duplicada."));

    }

    public static class InscricaoRequest {
        private Usuario usuario;
        private Evento evento;

        public Usuario getUsuario() {
            return usuario;
        }

        public void setUsuario(Usuario usuario) {
            this.usuario = usuario;
        }

        public Evento getEvento() {
            return evento;
        }

        public void setEvento(Evento evento) {
            this.evento = evento;
        }
    }
}

