package uv.fei.tutorias.domain;

// author @liu

import java.util.Objects;

public class Persona {
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

    public Persona(int idPersona, String nombre, String apellidoPaterno, String apellidoMaterno, String correoInstitucional) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correoInstitucional = correoInstitucional;
    }


    public Persona(String nombre, String apellidoPaterno, String apellidoMaterno,  String correoInstitucional) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correoInstitucional = correoInstitucional;
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
    public boolean equals(Object obj) {
        if(obj instanceof Persona) {
            Persona tmpPersona = (Persona)obj;
            if(this.idPersona == tmpPersona.getIdPersona() && this.nombre.equals(tmpPersona.getNombre())
                    && this.apellidoPaterno.equals(tmpPersona.getApellidoPaterno()) && this.apellidoMaterno.equals(tmpPersona.getApellidoMaterno()))
                   {
                return true;
            }
        }else {
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdPersona(), getNombre(), getApellidoPaterno(), getApellidoMaterno(), getCorreoInstitucional());
    }
}