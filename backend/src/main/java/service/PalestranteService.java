package service;

import dao.PalestranteDAO;
import model.Palestrante;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PalestranteService {
    private final PalestranteDAO palestranteDAO;

    public PalestranteService(PalestranteDAO palestranteDAO) {
        this.palestranteDAO = palestranteDAO;
    }

    public List<Palestrante> listarTodos() {
        return palestranteDAO.findAll();
    }
}
