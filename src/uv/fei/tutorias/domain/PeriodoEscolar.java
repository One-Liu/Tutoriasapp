package uv.fei.tutorias.domain;

// author @liu
public class PeriodoEscolar {
    private int idPeriodoEscolar;
    private String fechaInicio;
    private String fechaTermino;

    //Constructors of uv.fei.tutorias.domain.PeriodoEscolar
    public PeriodoEscolar() {
        this.fechaInicio = "";
        this.fechaTermino = "";
    }
    
    public PeriodoEscolar(String fechaInicio, String fechaTermino) {
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
    }
    
    public PeriodoEscolar(int idPeriodoEscolar, String fechaInicio, String fechaTermino) {
        this.idPeriodoEscolar = idPeriodoEscolar;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
    }
    
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
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof PeriodoEscolar) {
            PeriodoEscolar tmpPeriodoEscolar = (PeriodoEscolar)obj;
            if(this.idPeriodoEscolar == tmpPeriodoEscolar.getIdPeriodoEscolar()
                    && this.fechaInicio.equals(tmpPeriodoEscolar.getFechaInicio())
                    && this.fechaTermino.equals(tmpPeriodoEscolar.getFechaTermino())) {
                return true;
            }
        }
        return false;
    }
}