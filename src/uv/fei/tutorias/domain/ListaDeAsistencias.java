package uv.fei.tutorias.domain;

// author @liu
public class ListaDeAsistencias {
    private int idListaDeAsistencias;
    private SesionDeTutoriaAcademica sesionDeTutoriaAcademica;
    private Estudiante estudiante;

    // Getters of uv.fei.tutorias.domain.ListaDeAsistencias
    public int getIdListaDeAsistencias() {
        return idListaDeAsistencias;
    }

    public SesionDeTutoriaAcademica getSesionDeTutoriaAcademica() {
        return sesionDeTutoriaAcademica;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    // Setters of uv.fei.tutorias.domain.ListaDeAsistencias
    public void setIdListaDeAsistencias(int idListaDeAsistencias) {
        this.idListaDeAsistencias = idListaDeAsistencias;
    }

    public void setSesionDeTutoriaAcademica(SesionDeTutoriaAcademica sesionDeTutoriaAcademica) {
        this.sesionDeTutoriaAcademica = sesionDeTutoriaAcademica;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
}