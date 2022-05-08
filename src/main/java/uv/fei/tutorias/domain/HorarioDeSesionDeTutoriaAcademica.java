package uv.fei.tutorias.domain;

// author @liu
public class HorarioDeSesionDeTutoriaAcademica {
    private int idHorarioDeSesionDeTutoriaAcademica;
    private String hora;
    private Estudiante estudiante;
    private SesionDeTutoriaAcademica sesionDeTutoriaAcademica;

    // Getters of uv.fei.tutorias.domain.HorarioDeSesionDeTutoriaAcademica
    public int getIdHorarioDeSesionDeTutoriaAcademica() {
        return idHorarioDeSesionDeTutoriaAcademica;
    }

    public String getHora() {
        return hora;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public SesionDeTutoriaAcademica getSesionDeTutoriaAcademica() {
        return sesionDeTutoriaAcademica;
    }

    // Setters of uv.fei.tutorias.domain.HorarioDeSesionDeTutoriaAcademica
    public void setIdHorarioDeSesionDeTutoriaAcademica(int idHorarioDeSesionDeTutoriaAcademica) {
        this.idHorarioDeSesionDeTutoriaAcademica = idHorarioDeSesionDeTutoriaAcademica;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public void setSesionDeTutoriaAcademica(SesionDeTutoriaAcademica sesionDeTutoriaAcademica) {
        this.sesionDeTutoriaAcademica = sesionDeTutoriaAcademica;
    }
}