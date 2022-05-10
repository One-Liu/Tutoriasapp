package uv.fei.tutorias.domain;

// author @liu
public class TutorAcademico {
    private int idTutorAcademico;
    private Persona persona;
    private int idUsuario;

    // Constructors of uv.fei.tutorias.domain.TutorAcademico
    public TutorAcademico() {
        this.idTutorAcademico = 0;
        this.persona = new Persona();
        this.idUsuario = 0;
    }

    public TutorAcademico(Persona persona, int idUsuario) {
        idTutorAcademico = 0;
        this.persona = persona;
        this.idUsuario = idUsuario;
    }

    public TutorAcademico(int idTutorAcademico, Persona persona, int idUsuario) {
        this.idTutorAcademico = idTutorAcademico;
        this.persona = persona;
        this.idUsuario = idUsuario;
    }

    // Getters of uv.fei.tutorias.domain.TutorAcademico
    public int getIdTutorAcademico() {
        return idTutorAcademico;
    }

    public Persona getPersona() {
        return persona;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    // Setters of uv.fei.tutorias.domain.TutorAcademico
    public void setIdTutorAcademico(int idTutorAcademico) {
        this.idTutorAcademico = idTutorAcademico;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void setidUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TutorAcademico) {
            TutorAcademico tmpTutorAcademico = (TutorAcademico)obj;
            if(this.idTutorAcademico == tmpTutorAcademico.getIdTutorAcademico()
                    && this.persona.equals(tmpTutorAcademico.getPersona())
                    && this.idUsuario == tmpTutorAcademico.getIdUsuario()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return persona.getNombreCompleto();
    }
}