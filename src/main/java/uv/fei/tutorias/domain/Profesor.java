package uv.fei.tutorias.domain;

// author @liu
public class Profesor {
    private int idProfesor;
    private Persona persona;

    // Getters of uv.fei.tutorias.domain.Profesor
    public int getIdProfesor() {
        return idProfesor;
    }

    public Persona getPersona() {
        return persona;
    }

    // Setters of uv.fei.tutorias.domain.Profesor
    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}