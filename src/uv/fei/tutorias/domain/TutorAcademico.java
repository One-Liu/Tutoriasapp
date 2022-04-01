package uv.fei.tutorias.domain;

// author @liu
public class TutorAcademico {
    private int idTutorAcademico;
    private Persona persona;

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
}