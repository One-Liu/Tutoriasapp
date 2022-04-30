package uv.fei.tutorias.domain;

// author @liu
public class ExperienciaEducativa {
    private int idExperienciaEducativa;
    private String nombre;
    private Profesor profesor;

    // Getters of uv.fei.tutorias.domain.ExperienciaEducativa
    public int getIdExperienciaEducativa() {
        return idExperienciaEducativa;
    }

    public String getNombre() {
        return nombre;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    // Setters of uv.fei.tutorias.domain.ExperienciaEducativa
    public void setIdExperienciaEducativa(int idExperienciaEducativa) {
        this.idExperienciaEducativa = idExperienciaEducativa;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}