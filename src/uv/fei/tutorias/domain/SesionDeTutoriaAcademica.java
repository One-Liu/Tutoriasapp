package uv.fei.tutorias.domain;

// author @liu
public class SesionDeTutoriaAcademica {
    private int idSesionDeTutoriaAcademica;
    private String fecha;
    private String hora;
    private int idPeriodoEscolar;

    // Getters of uv.fei.tutorias.domain.SesionDeTutoriaAcademica
    public int getIdSesionDeTutoriaAcademica() {
        return idSesionDeTutoriaAcademica;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
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

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setIdPeriodoEscolar(int idPeriodoEscolar) {
        this.idPeriodoEscolar = idPeriodoEscolar;
    }
}