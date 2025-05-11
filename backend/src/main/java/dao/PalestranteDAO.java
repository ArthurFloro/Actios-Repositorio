package dao;

import model.Palestrante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PalestranteDAO extends JpaRepository<Palestrante, Integer> {
}