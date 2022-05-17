package uv.fei.tutorias.domain;

// author @liu
public class PeriodoEscolar {
    private int idPeriodoEscolar;
    private String fechaInicio;
    private String fechaTermino;

    // Getters of uv.fei.tutorias.domain.PeriodoEscolar
    public int getIdPeriodoEscolar() {
        return idPeriodoEscolar;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaTermino() {
        return fechaTermino;
    }

    // Setters of uv.fei.tutorias.domain.PeriodoEscolar
    public void setIdPeriodoEscolar(int idPeriodoEscolar) {
        this.idPeriodoEscolar = idPeriodoEscolar;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaTermino(String fechaTermino) {
        this.fechaTermino = fechaTermino;
    }
}
