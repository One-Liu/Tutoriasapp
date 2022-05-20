package uv.fei.tutorias.domain;

// author @liu

import java.util.Objects;

public class ExperienciaEducativa {
    private int idExperienciaEducativa;
    private String nombre;
    private String nrc;
    private int idProfesor;
    private Profesor profesor;

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public ExperienciaEducativa(String nombre, String nrc, int idProfesor) {
        this.nombre = nombre;
        this.nrc = nrc;
        this.idProfesor = idProfesor;
    }

    public ExperienciaEducativa(int idExperienciaEducativa, String nombre, int idProfesor) {
        this.idExperienciaEducativa = idExperienciaEducativa;
        this.nombre = nombre;
        this.idProfesor = idProfesor;
    }

    public ExperienciaEducativa(String nombre, String nrc) {
        this.nombre = nombre;
        this.nrc = nrc;
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

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExperienciaEducativa)) return false;

        ExperienciaEducativa that = (ExperienciaEducativa) o;

        if (getIdExperienciaEducativa() != that.getIdExperienciaEducativa()) return false;
        if (getIdProfesor() != that.getIdProfesor()) return false;
        return getNombre() != null ? getNombre().equals(that.getNombre()) : that.getNombre() == null;
    }

    @Override
    public int hashCode() {
        int result = getIdExperienciaEducativa();
        result = 31 * result + (getNombre() != null ? getNombre().hashCode() : 0);
        result = 31 * result + getIdProfesor();
        return result;
    }
}
