package uv.fei.tutorias.domain;

// author @liu
public class TutorAcademico {
    private int idTutorAcademico;
    private Persona persona;

    // Constructors of uv.fei.tutorias.domain.TutorAcademico
    public TutorAcademico() {
        this.idTutorAcademico = 0;
        this.persona = new Persona();
    }

    public TutorAcademico(Persona persona) {
        idTutorAcademico = 0;
        this.persona = persona;
    }

    public TutorAcademico(int idTutorAcademico, Persona persona) {
        this.idTutorAcademico = idTutorAcademico;
        this.persona = persona;
    }

    // Getters of uv.fei.tutorias.domain.TutorAcademico
    public int getIdTutorAcademico() {
        return idTutorAcademico;
    }

    public Persona getPersona() {
        return persona;
    }

    // Setters of uv.fei.tutorias.domain.TutorAcademico
    public void setIdTutorAcademico(int idTutorAcademico) {
        this.idTutorAcademico = idTutorAcademico;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TutorAcademico) {
            TutorAcademico tmpTutorAcademico = (TutorAcademico)obj;
            return this.idTutorAcademico == tmpTutorAcademico.getIdTutorAcademico()
                    && this.persona.equals(tmpTutorAcademico.getPersona());
        }
        return false;
    }

    @Override
    public String toString() {
        return persona.getNombreCompleto();
    }
}