package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(EventoPalestranteId.class)
public class EventoPalestrante {
    @Id
    private Integer eventoId;

    @Id
    private Integer palestranteId;

    @ManyToOne
    @JoinColumn(name = "evento_id", insertable = false, updatable = false)
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "palestrante_id", insertable = false, updatable = false)
    private Palestrante palestrante;

    // Getters e setters
    public Integer getEventoId() {
        return eventoId;
    }

    public void setEventoId(Integer eventoId) {
        this.eventoId = eventoId;
    }

    public Integer getPalestranteId() {
        return palestranteId;
    }

    public void setPalestranteId(Integer palestranteId) {
        this.palestranteId = palestranteId;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Palestrante getPalestrante() {
        return palestrante;
    }

    public void setPalestrante(Palestrante palestrante) {
        this.palestrante = palestrante;
    }
}
