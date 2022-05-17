package uv.fei.tutorias.domain;

// author @liu
public class ListaDeAsistencia {
    private int idListaDeAsistencia;
    private String hora;
    private int idSesionDeTutoriaAcademica;
    private int idEstudiante;

    // Constructors of uv.fei.tutorias.domain.ListaDeAsistencia
    public ListaDeAsistencia() {
        this.idListaDeAsistencia = 0;
        this.hora = "";
        this.idSesionDeTutoriaAcademica = 0;
        this.idEstudiante = 0;
    }

    public ListaDeAsistencia(String hora, int idSesionDeTutoriaAcademica, int idEstudiante) {
        this.idListaDeAsistencia = 0;
        this.hora = hora;
        this.idSesionDeTutoriaAcademica = idSesionDeTutoriaAcademica;
        this.idEstudiante = idEstudiante;
    }

    public ListaDeAsistencia(int idListaDeAsistencia, String hora, int idSesionDeTutoriaAcademica, int idEstudiante) {
        this.idListaDeAsistencia = idListaDeAsistencia;
        this.hora = hora;
        this.idSesionDeTutoriaAcademica = idSesionDeTutoriaAcademica;
        this.idEstudiante = idEstudiante;
    }

    // Getters of uv.fei.tutorias.domain.ListaDeAsistencia
    public int getIdListaDeAsistencia() {
        return idListaDeAsistencia;
    }

    public String getHora() {
        return hora;
    }

    public int getIdSesionDeTutoriaAcademica() {
        return idSesionDeTutoriaAcademica;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    // Setters of uv.fei.tutorias.domain.ListaDeAsistencia
    public void setIdListaDeAsistencia(int idListaDeAsistencia) {
        this.idListaDeAsistencia = idListaDeAsistencia;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setIdSesionDeTutoriaAcademica(int idSesionDeTutoriaAcademica) {
        this.idSesionDeTutoriaAcademica = idSesionDeTutoriaAcademica;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ListaDeAsistencia) {
            ListaDeAsistencia tmpListaDeAsistencia = (ListaDeAsistencia)obj;
            if(this.idListaDeAsistencia == tmpListaDeAsistencia.getIdListaDeAsistencia()
                    && this.hora.equals(tmpListaDeAsistencia.getHora())
                    && this.idSesionDeTutoriaAcademica == tmpListaDeAsistencia.getIdSesionDeTutoriaAcademica()
                    && this.idEstudiante == tmpListaDeAsistencia.getIdEstudiante()) {
                return true;
            }
        }
        return false;
    }
}