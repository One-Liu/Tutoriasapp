package uv.fei.tutorias.domain;

// author @liu
public class SesionDeTutoriaAcademica {
    private int idSesionDeTutoriaAcademica;
    private String fecha;
    private PeriodoEscolar periodoEscolar;

    //Constructors of uv.fei.tutorias.domain.SesionDeTutoriaAcademica
    public SesionDeTutoriaAcademica() {
        this.idSesionDeTutoriaAcademica = 0;
        this.fecha = "";
        this.periodoEscolar = null;
    }

    public SesionDeTutoriaAcademica(int idSesionDeTutoriaAcademica, String fecha, PeriodoEscolar periodoEscolar) {
        this.idSesionDeTutoriaAcademica = idSesionDeTutoriaAcademica;
        this.fecha = fecha;
        this.periodoEscolar = periodoEscolar;
    }

    public SesionDeTutoriaAcademica(String fecha, PeriodoEscolar periodoEscolar) {
        this.idSesionDeTutoriaAcademica = 0;
        this.fecha = fecha;
        this.periodoEscolar = periodoEscolar;
    }
    
    // Getters of uv.fei.tutorias.domain.SesionDeTutoriaAcademica
    public int getIdSesionDeTutoriaAcademica() {
        return idSesionDeTutoriaAcademica;
    }

    public String getFecha() {
        return fecha;
    }

    public PeriodoEscolar getPeriodoEscolar() {
        return periodoEscolar;
    }

    // Setters of uv.fei.tutorias.domain.SesionDeTutoriaAcademica
    public void setIdSesionDeTutoriaAcademica(int idSesionDeTutoriaAcademica) {
        this.idSesionDeTutoriaAcademica = idSesionDeTutoriaAcademica;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setPeriodoEscolar(PeriodoEscolar periodoEscolar) {
        this.periodoEscolar = periodoEscolar;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof SesionDeTutoriaAcademica) {
            SesionDeTutoriaAcademica tmpSesionDeTutoriaAcademica = (SesionDeTutoriaAcademica)obj;
            if(this.idSesionDeTutoriaAcademica == tmpSesionDeTutoriaAcademica.getIdSesionDeTutoriaAcademica()
                    && this.fecha.equals(tmpSesionDeTutoriaAcademica.getFecha())
                    && this.periodoEscolar.equals(tmpSesionDeTutoriaAcademica.getPeriodoEscolar())) {
                return true;
            }
        }
        return false;
    }
    
    
}