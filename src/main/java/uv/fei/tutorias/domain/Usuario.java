package uv.fei.tutorias.domain;

public class Usuario extends Persona {
    int idUsuario;
    String contrasena;
    String correoInstitucional;

    public Usuario() {
        super();
        this.idUsuario = 0;
        this.contrasena = "";
        this.correoInstitucional = "";
    }
    
    public Usuario(String contrasena, String correoInstitucional) {
        super();
        this.idUsuario = 0;
        this.contrasena = contrasena;
        this.correoInstitucional = correoInstitucional;
    }
    
    public Usuario(Persona usuario) {
        super(usuario.getIdPersona(), usuario.getNombre(), usuario.getApellidoPaterno(), usuario.getApellidoMaterno(), usuario.getIdProgramaEducativo());
        this.idUsuario = 0;
        this.contrasena = "";
        this.correoInstitucional = "";
    }
    
    public Usuario(int idUsuario, String contrasena, String correoInstitucional, Persona usuario) {
        super(usuario.getIdPersona(), usuario.getNombre(), usuario.getApellidoPaterno(), usuario.getApellidoMaterno(), usuario.getIdProgramaEducativo());
        this.idUsuario = idUsuario;
        this.contrasena = contrasena;
        this.correoInstitucional = correoInstitucional;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int id) {
        this.idUsuario = id;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Usuario) {
            Usuario tmpUsuario = (Usuario) obj;
            if(this.idUsuario == tmpUsuario.getIdUsuario()
                    && this.contrasena.equals(tmpUsuario.getContrasena())
                    && this.correoInstitucional.equals(tmpUsuario.getCorreoInstitucional())
                    && this.idPersona == tmpUsuario.getIdPersona()
                    && this.nombre.equals(tmpUsuario.getNombre())
                    && this.apellidoPaterno.equals(tmpUsuario.getApellidoPaterno())
                    && this.apellidoMaterno.equals(tmpUsuario.getApellidoMaterno())
                    && this.idProgramaEducativo == tmpUsuario.getIdProgramaEducativo()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = getIdUsuario();
        result = 31 * result + (getContrasena() != null ? getContrasena().hashCode() : 0);
        result = 31 * result + (getCorreoInstitucional() != null ? getCorreoInstitucional().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + idUsuario +
                ", contrasena='" + contrasena + '\'' +
                ", correoInstitucional='" + correoInstitucional + '\'' +
                '}';
    }
}
