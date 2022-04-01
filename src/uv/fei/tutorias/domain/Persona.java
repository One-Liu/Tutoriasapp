package uv.fei.tutorias.domain;

// author @liu
public class Persona {
    private int idPersona;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correoInstitucional;
    private String correoPersonal;

    public Persona() {
        this.idPersona = 0;
        this.nombre = "";
        this.apellidoPaterno = "";
        this.apellidoMaterno = "";
        this.correoInstitucional = "";
        this.correoPersonal = "";
    }

    public Persona(int idPersona, String nombre, String apellidoPaterno, String apellidoMaterno, String correoInstitucional, String correoPersonal) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correoInstitucional = correoInstitucional;
        this.correoPersonal = correoPersonal;
    }

    public Persona(String nombre, String apellidoPaterno, String apellidoMaterno, String correoInstitucional, String correoPersonal) {
        this.idPersona = 0;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correoInstitucional = correoInstitucional;
        this.correoPersonal = correoPersonal;
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

    public String getCorreoPersonal() {
        return correoPersonal;
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

    public void setCorreoPersonal(String correoPersonal) {
        this.correoPersonal = correoPersonal;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Persona) {
            Persona tmpPersona = (Persona)obj;
            if(this.idPersona == tmpPersona.getIdPersona() 
                    && this.nombre.equals(tmpPersona.getNombre()) 
                    && this.apellidoPaterno.equals(tmpPersona.getApellidoPaterno()) 
                    && this.apellidoMaterno.equals(tmpPersona.getApellidoMaterno()) 
                    && this.correoInstitucional.equals(tmpPersona.getCorreoInstitucional())
                    && this.correoPersonal.equals(tmpPersona.getCorreoPersonal())) {
                return true;
            }
        }
        return false;
    }
}