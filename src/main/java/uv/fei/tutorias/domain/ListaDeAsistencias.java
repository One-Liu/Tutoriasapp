package uv.fei.tutorias.domain;

// author @liu
public class ListaDeAsistencias {
    private int idListaDeAsistencias;
    private int idSesionDeTutoriaAcademica;
    private int idEstudiante;

    // Getters of uv.fei.tutorias.domain.ListaDeAsistencias
    public int getIdListaDeAsistencias() {
        return idListaDeAsistencias;
    }

    public int getIdSesionDeTutoriaAcademica() {
        return idSesionDeTutoriaAcademica;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    // Setters of uv.fei.tutorias.domain.ListaDeAsistencias
    public void setIdListaDeAsistencias(int idListaDeAsistencias) {
        this.idListaDeAsistencias = idListaDeAsistencias;
    }

    public void setIdSesionDeTutoriaAcademica(int idSesionDeTutoriaAcademica) {
        this.idSesionDeTutoriaAcademica = idSesionDeTutoriaAcademica;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }
}