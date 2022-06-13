package uv.fei.tutorias.domain;

// author @liu
public class TutorAcademico extends Usuario {
    private int idTutorAcademico;

    public TutorAcademico() {
        this.idTutorAcademico = 0;
    }

    public TutorAcademico(Persona tutorAcademico) {
        super(tutorAcademico);
        this.idTutorAcademico = 0;
    }
    
    public TutorAcademico(int idTutorAcademico, Persona tutorAcademico) {
        super(tutorAcademico);
        this.idTutorAcademico = idTutorAcademico;
    }

    public TutorAcademico(Persona tutorAcademico, Usuario usuario) {
        super(usuario.getIdUsuario(), usuario.getContrasena(), usuario.getCorreoInstitucional(), tutorAcademico);
        this.idTutorAcademico = 0;
    }
    
    public int getIdTutorAcademico() {
        return idTutorAcademico;
    }

    public void setIdTutorAcademico(int id) {
        this.idTutorAcademico = id;
    }

    @Override
    public String toString() {
        return "TutorAcademico{" +
                "id=" + idTutorAcademico +
                ", Persona="+getNombreCompleto()+
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TutorAcademico) {
            TutorAcademico tmpTutorAcademico = (TutorAcademico)obj;
            if(this.idTutorAcademico == tmpTutorAcademico.getIdTutorAcademico()
                    && this.idUsuario == tmpTutorAcademico.getIdUsuario()
                    && this.contrasena.equals(tmpTutorAcademico.getContrasena())
                    && this.correoInstitucional.equals(tmpTutorAcademico.getCorreoInstitucional())
                    && this.idPersona == tmpTutorAcademico.getIdPersona()
                    && this.nombre.equals(tmpTutorAcademico.getNombre())
                    && this.apellidoPaterno.equals(tmpTutorAcademico.getApellidoPaterno())
                    && this.apellidoMaterno.equals(tmpTutorAcademico.getApellidoMaterno())
                    && this.idProgramaEducativo == tmpTutorAcademico.getIdProgramaEducativo()) {
                return true;
            }
        }
        return false;
    }

}