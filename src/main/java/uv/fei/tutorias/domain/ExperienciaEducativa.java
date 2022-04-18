package uv.fei.tutorias.domain;

// author @liu

public class ExperienciaEducativa {
    private int idExperienciaEducativa;
    private String nombre;
    private int idProfesor;

    public ExperienciaEducativa(int idExperienciaEducativa, String nombre, int idProfesor) {
        this.idExperienciaEducativa = idExperienciaEducativa;
        this.nombre = nombre;
        this.idProfesor = idProfesor;
    }

    public ExperienciaEducativa() {
        this.idExperienciaEducativa = 0;
        this.nombre = "";
        this.idProfesor = 0;
    }

    public ExperienciaEducativa(String nombre, int idProfesor) {
        this.nombre = nombre;
        this.idProfesor = idProfesor;
    }

    // Getters of uv.fei.tutorias.domain.ExperienciaEducativa
    public int getIdExperienciaEducativa() {
        return idExperienciaEducativa;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    // Setters of uv.fei.tutorias.domain.ExperienciaEducativa
    public void setIdExperienciaEducativa(int idExperienciaEducativa) {
        this.idExperienciaEducativa = idExperienciaEducativa;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }
}