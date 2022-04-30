package uv.fei.tutorias.domain;

// author @liu
public class ListaDeAsistencias {
    private int idListaDeAsistencias;
    private int idSesionDeTutoriaAcademica;
    private int idEstudiante;

    // Constructors of uv.fei.tutorias.domain.ListaDeAsistencias
    public ListaDeAsistencias() {
        this.idListaDeAsistencias = 0;
        this.idSesionDeTutoriaAcademica = 0;
        this.idEstudiante = 0;
    }

    public ListaDeAsistencias(int idSesionDeTutoriaAcademica, int idEstudiante) {
        this.idListaDeAsistencias = 0;
        this.idSesionDeTutoriaAcademica = idSesionDeTutoriaAcademica;
        this.idEstudiante = idEstudiante;
    }

    public ListaDeAsistencias(int idListaDeAsistencias, int idSesionDeTutoriaAcademica, int idEstudiante) {
        this.idListaDeAsistencias = idListaDeAsistencias;
        this.idSesionDeTutoriaAcademica = idSesionDeTutoriaAcademica;
        this.idEstudiante = idEstudiante;
    }

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

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ListaDeAsistencias) {
            ListaDeAsistencias tmpListaDeAsistencias = (ListaDeAsistencias)obj;
            if(this.idListaDeAsistencias == tmpListaDeAsistencias.getIdListaDeAsistencias()
                    && this.idSesionDeTutoriaAcademica == tmpListaDeAsistencias.getIdSesionDeTutoriaAcademica()
                    && this.idEstudiante == tmpListaDeAsistencias.getIdEstudiante()) {
                return true;
            }
        }
        return false;
    }
}