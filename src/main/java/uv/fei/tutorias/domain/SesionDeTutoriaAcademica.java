package uv.fei.tutorias.domain;

// author @liu
public class SesionDeTutoriaAcademica {
    private int idSesionDeTutoriaAcademica;
    private String fecha;
    private int idPeriodoEscolar;

    //Constructors of uv.fei.tutorias.domain.SesionDeTutoriaAcademica
    public SesionDeTutoriaAcademica() {
        this.idSesionDeTutoriaAcademica = 0;
        this.fecha = "";
        this.idPeriodoEscolar = 0;
    }

    public SesionDeTutoriaAcademica(String fecha, int idPeriodoEscolar) {
        this.idSesionDeTutoriaAcademica = 0;
        this.fecha = fecha;
        this.idPeriodoEscolar = idPeriodoEscolar;
    }

    public SesionDeTutoriaAcademica(int idSesionDeTutoriaAcademica, String fecha, int idPeriodoEscolar) {
        this.idSesionDeTutoriaAcademica = idSesionDeTutoriaAcademica;
        this.fecha = fecha;
        this.idPeriodoEscolar = idPeriodoEscolar;
    }
    
    // Getters of uv.fei.tutorias.domain.SesionDeTutoriaAcademica
    public int getIdSesionDeTutoriaAcademica() {
        return idSesionDeTutoriaAcademica;
    }

    public String getFecha() {
        return fecha;
    }

    public int getIdPeriodoEscolar() {
        return idPeriodoEscolar;
    }

    // Setters of uv.fei.tutorias.domain.SesionDeTutoriaAcademica
    public void setIdSesionDeTutoriaAcademica(int idSesionDeTutoriaAcademica) {
        this.idSesionDeTutoriaAcademica = idSesionDeTutoriaAcademica;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setIdPeriodoEscolar(int idPeriodoEscolar) {
        this.idPeriodoEscolar = idPeriodoEscolar;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof SesionDeTutoriaAcademica) {
            SesionDeTutoriaAcademica tmpSesionDeTutoriaAcademica = (SesionDeTutoriaAcademica)obj;
            if(this.idSesionDeTutoriaAcademica == tmpSesionDeTutoriaAcademica.getIdSesionDeTutoriaAcademica()
                    && this.fecha.equals(tmpSesionDeTutoriaAcademica.getFecha())
                    && this.idPeriodoEscolar == tmpSesionDeTutoriaAcademica.getIdPeriodoEscolar()) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        return getFecha();
    }
}