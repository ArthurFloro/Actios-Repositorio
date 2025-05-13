package dao;

import model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventoDAO extends JpaRepository<Evento, Integer> {
    List<Evento> findByDataFimBefore(LocalDateTime data);
}
