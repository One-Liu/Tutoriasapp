package uv.fei.tutorias.domain;

public class  Persona {
    protected int idPersona;
    protected String nombre;
    protected String apellidoPaterno;
    protected String apellidoMaterno;
    protected int idProgramaEducativo;

    public Persona() {
        this.idPersona = 0;
        this.nombre = "";
        this.apellidoPaterno = "";
        this.apellidoMaterno = "";
        this.idProgramaEducativo = 0;
    }
    
    public Persona(String nombre, String apellidoPaterno, String apellidoMaterno, int idProgramaEducativo) {
        this.idPersona = 0;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.idProgramaEducativo = idProgramaEducativo;
    }

    public Persona(int idPersona, String nombre, String apellidoPaterno, String apellidoMaterno, int idProgramaEducativo) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.idProgramaEducativo = idProgramaEducativo;
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

    public int getIdProgramaEducativo() {
        return idProgramaEducativo;
    }

    public void setIdProgramaEducativo(int idProgramaEducativo) {
        this.idProgramaEducativo = idProgramaEducativo;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Persona) {
            Persona tmpPersona = (Persona) obj;
            if(this.idPersona == tmpPersona.getIdPersona()
                    && this.nombre.equals(tmpPersona.getNombre())
                    && this.apellidoPaterno.equals(tmpPersona.getApellidoPaterno())
                    && this.apellidoMaterno.equals(tmpPersona.getApellidoMaterno())
                    && this.idProgramaEducativo == tmpPersona.getIdProgramaEducativo()) {
                return true;
            } 
        }
        return false;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "idPersona=" + idPersona +
                ", nombre='" + nombre + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", idProgramaEducativo='" + idProgramaEducativo + "'\''" +
                '}';
    }
}