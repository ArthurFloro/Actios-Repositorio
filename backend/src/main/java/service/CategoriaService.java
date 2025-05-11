package service;

import dao.CategoriaDAO;
import model.Categoria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaDAO categoriaDAO;

    public CategoriaService(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    public boolean nomeExiste(String nome) {
        return categoriaDAO.existsByNome(nome);
    }

    public List<Categoria> listarTodas() {
        return categoriaDAO.findAll();
    }
}

