package uv.fei.tutorias.domain;

// author @liu

public class ExperienciaEducativa {
    private int idExperienciaEducativa;
    private String nombre;
    private int idProfesor;

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