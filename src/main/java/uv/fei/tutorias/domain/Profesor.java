package uv.fei.tutorias.domain;

// author @liu

public class Profesor {
    private int idProfesor;
    private Persona persona;

    public Profesor(int idProfesor, Persona persona) {
        this.idProfesor = idProfesor;
        this.persona = persona;
    }

    public Profesor() {

    }

    // Getters of uv.fei.tutorias.domain.Profesor
    public int getIdProfesor() {
        return idProfesor;
    }


    // Setters of uv.fei.tutorias.domain.Profesor
    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}