package uv.fei.tutorias.domain;

// author @liu
public class ListaDeAsistencia {
    private int id;
    private String hora;
    private boolean asistio;
    private int idSesionDeTutoriaAcademica;
    private int idEstudiante;

    public ListaDeAsistencia() {
        this.id = 0;
        this.hora = "";
        this.idSesionDeTutoriaAcademica = 0;
        this.idEstudiante = 0;
    }

    public ListaDeAsistencia(String hora, boolean asistio, int idSesionDeTutoriaAcademica, int idEstudiante) {
        this.id = 0;
        this.hora = hora;
        this.asistio = asistio;
        this.idSesionDeTutoriaAcademica = idSesionDeTutoriaAcademica;
        this.idEstudiante = idEstudiante;
    }

    public ListaDeAsistencia(int idListaDeAsistencia, String hora, boolean asistio, int idSesionDeTutoriaAcademica, int idEstudiante) {
        this.id = idListaDeAsistencia;
        this.hora = hora;
        this.asistio = asistio;
        this.idSesionDeTutoriaAcademica = idSesionDeTutoriaAcademica;
        this.idEstudiante = idEstudiante;
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
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
    
    public boolean getAsistio() {
        return asistio;
    }

    public void setAsistio(boolean asistio) {
        this.asistio = asistio;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ListaDeAsistencia) {
            ListaDeAsistencia tmpListaDeAsistencia = (ListaDeAsistencia)obj;
            if(this.id == tmpListaDeAsistencia.getId()
                    && this.hora.equals(tmpListaDeAsistencia.getHora())
                    && this.asistio == tmpListaDeAsistencia.getAsistio()
                    && this.idSesionDeTutoriaAcademica == tmpListaDeAsistencia.getIdSesionDeTutoriaAcademica()
                    && this.idEstudiante == tmpListaDeAsistencia.getIdEstudiante()) {
                return true;
            }
        }
        return false;
    }
}