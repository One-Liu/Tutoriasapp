package uv.fei.tutorias.domain;

// author @liu

import java.util.Objects;

public class  Persona {
    private int idPersona;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correoInstitucional;
    private String correoPersonal;

    public Persona(String nombre, String apellidoPaterno, String apellidoMaterno, String correoInstitucional, String correoPersonal) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correoInstitucional = correoInstitucional;
        this.correoPersonal = correoPersonal;
    }
    public Persona(int idPersona, String nombre, String apellidoPaterno, String apellidoMaterno, String correoInstitucional, String correoPersonal) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correoInstitucional = correoInstitucional;
        this.correoPersonal = correoPersonal;
    }

    public String getCorreoPersonal() {
        return correoPersonal;
    }

    public void setCorreoPersonal(String correoPersonal) {
        this.correoPersonal = correoPersonal;
    }

    public Persona() {
        nombre = "";
        apellidoPaterno = "";
        apellidoMaterno = "";
        correoInstitucional = "";
    }




    // Getters of uv.fei.tutorias.domain.Persona
    public int getIdPersona() {
        return idPersona;
    }


    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    // Setters of uv.fei.tutorias.domain.Persona
    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona)) return false;

        Persona persona = (Persona) o;

        if (getNombre() != null ? !getNombre().equals(persona.getNombre()) : persona.getNombre() != null) return false;
        if (getApellidoPaterno() != null ? !getApellidoPaterno().equals(persona.getApellidoPaterno()) : persona.getApellidoPaterno() != null)
            return false;
        if (getApellidoMaterno() != null ? !getApellidoMaterno().equals(persona.getApellidoMaterno()) : persona.getApellidoMaterno() != null)
            return false;
        if (getCorreoInstitucional() != null ? !getCorreoInstitucional().equals(persona.getCorreoInstitucional()) : persona.getCorreoInstitucional() != null)
            return false;
        return getCorreoPersonal() != null ? getCorreoPersonal().equals(persona.getCorreoPersonal()) : persona.getCorreoPersonal() == null;
    }




    @Override
    public int hashCode() {
        return Objects.hash(getIdPersona(), getNombre(), getApellidoPaterno(), getApellidoMaterno(), getCorreoInstitucional());
    }
}