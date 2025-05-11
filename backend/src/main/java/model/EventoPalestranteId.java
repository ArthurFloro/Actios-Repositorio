package model;

import java.io.Serializable;
import java.util.Objects;

public class EventoPalestranteId implements Serializable {
    private Integer eventoId;
    private Integer palestranteId;

    // Getters e Setters
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

    // equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventoPalestranteId that = (EventoPalestranteId) o;
        return Objects.equals(eventoId, that.eventoId) && 
               Objects.equals(palestranteId, that.palestranteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventoId, palestranteId);
    }
}
