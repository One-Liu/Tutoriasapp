package uv.fei.tutorias.domain;

public class Usuario extends Persona {
    int idUsuario;
    String contrasena;
    String correoInstitucional;

    public Usuario(String nombre, String apellidoPaterno, String apellidoMaterno) {
        super(nombre, apellidoPaterno, apellidoMaterno);
    }
    
    public Usuario(int idUsuario, String contrasena, String correoInstitucional, String nombre, String apellidoPaterno, String apellidoMaterno) {
        super(nombre, apellidoPaterno, apellidoMaterno);
        this.idUsuario = idUsuario;
        this.contrasena = contrasena;
        this.correoInstitucional = correoInstitucional;
    }
    
    public Usuario(String contrasena, String correoInstitucional) {
        this.contrasena = contrasena;
        this.correoInstitucional = correoInstitucional;
    }

    public Usuario() {
        this.idUsuario = 0;
        this.contrasena = "";
        this.correoInstitucional = "";
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;

        Usuario usuario = (Usuario) o;

        if (getIdUsuario() != usuario.getIdUsuario()) return false;
        if (getContrasena() != null ? !getContrasena().equals(usuario.getContrasena()) : usuario.getContrasena() != null)
            return false;
        return getCorreoInstitucional() != null ? getCorreoInstitucional().equals(usuario.getCorreoInstitucional()) : usuario.getCorreoInstitucional() == null;
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
