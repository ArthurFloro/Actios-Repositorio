package service;

import dao.FaculdadeDAO;
import model.Faculdade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaculdadeService {
    private final FaculdadeDAO faculdadeDAO;

    public FaculdadeService(FaculdadeDAO faculdadeDAO) {
        this.faculdadeDAO = faculdadeDAO;
    }

    public List<Faculdade> listarTodas() {
        return faculdadeDAO.findAll();
    }
}
