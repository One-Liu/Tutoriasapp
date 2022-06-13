package uv.fei.tutorias.domain;

// author @liu
public class Coordinador extends Usuario {
    private int idCoordinador;

    public Coordinador() {
        super();
        this.idCoordinador = 0;
        this.idProgramaEducativo = 0;
    }

    public Coordinador(int idCoordinador, Persona coordinador) {
        super(coordinador);
        this.idCoordinador = idCoordinador;
    }
    
    public Coordinador(int idCoordinador, Persona coordinador, Usuario usuario) {
        super(usuario.getIdUsuario(), usuario.getContrasena(), usuario.getCorreoInstitucional(), coordinador);
        this.idCoordinador = idCoordinador;
    }

    public int getIdCoordinador() {
        return idCoordinador;
    }

    public void setIdCoordinador(int idCoordinador) {
        this.idCoordinador = idCoordinador;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Coordinador) {
            Coordinador tmpCoordinador = (Coordinador)obj;
            if(this.idCoordinador == tmpCoordinador.getIdCoordinador()
                    && this.idUsuario == tmpCoordinador.getIdUsuario()
                    && this.contrasena.equals(tmpCoordinador.getContrasena())
                    && this.correoInstitucional.equals(tmpCoordinador.getCorreoInstitucional())
                    && this.idPersona == tmpCoordinador.getIdPersona()
                    && this.nombre.equals(tmpCoordinador.getNombre())
                    && this.apellidoPaterno.equals(tmpCoordinador.getApellidoPaterno())
                    && this.apellidoMaterno.equals(tmpCoordinador.getApellidoMaterno())
                    && this.idProgramaEducativo == tmpCoordinador.getIdProgramaEducativo()) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "Coordinador{" +
                "id=" + idCoordinador +
                ", Persona="+getNombreCompleto()+
                '}';
    }
}