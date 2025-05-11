package controller;

import model.Faculdade;
import service.FaculdadeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculdades")
public class FaculdadeController {
    private final FaculdadeService faculdadeService;

    public FaculdadeController(FaculdadeService faculdadeService) {
        this.faculdadeService = faculdadeService;
    }

    @GetMapping
    public List<Faculdade> listarTodas() {
        return faculdadeService.listarTodas();
    }
}
