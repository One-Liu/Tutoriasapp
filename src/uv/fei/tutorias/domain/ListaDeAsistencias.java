package uv.fei.tutorias.domain;

// author @liu
public class ListaDeAsistencias {
    private int idListaDeAsistencias;
    private SesionDeTutoriaAcademica sesionDeTutoriaAcademica;
    private Estudiante estudiante;

    public ListaDeAsistencias() {
        this.idListaDeAsistencias = 0;
        this.sesionDeTutoriaAcademica = null;
        this.estudiante = null;
    }

    public ListaDeAsistencias(int idListaDeAsistencias, SesionDeTutoriaAcademica sesionDeTutoriaAcademica, Estudiante estudiante) {
        this.idListaDeAsistencias = idListaDeAsistencias;
        this.sesionDeTutoriaAcademica = sesionDeTutoriaAcademica;
        this.estudiante = estudiante;
    }

    public ListaDeAsistencias(SesionDeTutoriaAcademica sesionDeTutoriaAcademica, Estudiante estudiante) {
        this.idListaDeAsistencias = 0;
        this.sesionDeTutoriaAcademica = sesionDeTutoriaAcademica;
        this.estudiante = estudiante;
    }
    
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
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ListaDeAsistencias) {
            ListaDeAsistencias tmpListaDeAsistencias = (ListaDeAsistencias)obj;
            if(this.idListaDeAsistencias == tmpListaDeAsistencias.getIdListaDeAsistencias()
                    && this.sesionDeTutoriaAcademica.equals(tmpListaDeAsistencias.getSesionDeTutoriaAcademica())
                    && this.estudiante.equals(tmpListaDeAsistencias.getEstudiante())) {
                return true;
            }
        }
        return false;
    }
}