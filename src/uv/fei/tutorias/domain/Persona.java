package uv.fei.tutorias.domain;

// author @liu
public class Persona {
    private int idPersona;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String correoInstitucional;

    public Persona() {
        nombre = "";
        apellidoPaterno = "";
        apellidoMaterno = "";
        telefono = "";
        correoInstitucional = "";
    }

    public Persona(int idPersona, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correoInstitucional) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.correoInstitucional = correoInstitucional;
    }

    public Persona(String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correoInstitucional) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
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

    public String getTelefono() {
        return telefono;
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

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Persona) {
            Persona tmpPersona = (Persona)obj;
            if(this.idPersona == tmpPersona.getIdPersona() && this.nombre.equals(tmpPersona.getNombre()) 
                    && this.apellidoPaterno.equals(tmpPersona.getApellidoPaterno()) && this.apellidoMaterno.equals(tmpPersona.getApellidoMaterno())
                    && this.telefono.equals(tmpPersona.getTelefono()) && this.correoInstitucional.equals(tmpPersona.getCorreoInstitucional())) {
                return true;
            }
        }
        return false;
    }
}