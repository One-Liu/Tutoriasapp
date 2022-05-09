package uv.fei.tutorias.domain;

// author @liu
public class Persona {
    private int idPersona;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;

    public Persona() {
        this.idPersona = 0;
        this.nombre = "";
        this.apellidoPaterno = "";
        this.apellidoMaterno = "";
    }

    public Persona(int idPersona, String nombre, String apellidoPaterno, String apellidoMaterno) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    public Persona(String nombre, String apellidoPaterno, String apellidoMaterno) {
        this.idPersona = 0;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
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

    public String getNombreCompleto() {
        return getApellidoPaterno() + " " + getApellidoMaterno() + " " + getNombre();
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

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Persona) {
            Persona tmpPersona = (Persona)obj;
            if(this.idPersona == tmpPersona.getIdPersona() 
                    && this.nombre.equals(tmpPersona.getNombre()) 
                    && this.apellidoPaterno.equals(tmpPersona.getApellidoPaterno()) 
                    && this.apellidoMaterno.equals(tmpPersona.getApellidoMaterno())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return getNombreCompleto();
    }
}