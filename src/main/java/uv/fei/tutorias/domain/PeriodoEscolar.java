package uv.fei.tutorias.domain;

// author @liu
public class PeriodoEscolar {
    private int id;
    private String fechaInicio;
    private String fechaTermino;

    public PeriodoEscolar() {
    }
    
    public PeriodoEscolar(int id, String fechaInicio, String fechaTermino) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
    }
    
    // Getters of uv.fei.tutorias.domain.PeriodoEscolar
    public int getId() {
        return id;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaTermino() {
        return fechaTermino;
    }

    // Setters of uv.fei.tutorias.domain.PeriodoEscolar
    public void setId(int id) {
        this.id = id;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaTermino(String fechaTermino) {
        this.fechaTermino = fechaTermino;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof PeriodoEscolar) {
            PeriodoEscolar tmpPeriodoEscolar = (PeriodoEscolar)obj;
            if(this.id == tmpPeriodoEscolar.getId()
                    && this.fechaInicio.equals(tmpPeriodoEscolar.getFechaInicio())
                    && this.fechaTermino.equals(tmpPeriodoEscolar.getFechaTermino())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return getFechaInicio() + " - " + getFechaTermino();
    }
}
