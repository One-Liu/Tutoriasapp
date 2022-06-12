package uv.fei.tutorias.domain;

public class  Persona {
    protected int idPersona;
    protected String nombre;
    protected String apellidoPaterno;
    protected String apellidoMaterno;

    public Persona(String nombre, String apellidoPaterno, String apellidoMaterno) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    public Persona() {
    }

    public Persona(int idPersona, String nombre, String apellidoPaterno, String apellidoMaterno) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNombreCompleto() {
        return this.nombre + " " + this.apellidoPaterno + " " + this.apellidoMaterno;
    }
    
    @Override
    public String toString() {
        return "Persona{" +
                "idPersona=" + idPersona +
                ", nombre='" + nombre + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona)) return false;

        Persona persona = (Persona) o;

        if (getIdPersona() != persona.getIdPersona()) return false;
        if (getNombre() != null ? !getNombre().equals(persona.getNombre()) : persona.getNombre() != null) return false;
        if (getApellidoPaterno() != null ? !getApellidoPaterno().equals(persona.getApellidoPaterno()) : persona.getApellidoPaterno() != null)
            return false;
        return getApellidoMaterno() != null ? getApellidoMaterno().equals(persona.getApellidoMaterno()) : persona.getApellidoMaterno() == null;
    }

}