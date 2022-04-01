package uv.fei.tutorias.domain;

// author @liu
public class SesionDeTutoriaAcademica {
    private int idSesionDeTutoriaAcademica;
    private String fecha;
    private PeriodoEscolar periodoEscolar;

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
}