package uv.fei.tutorias.domain;

public class Usuario extends Persona{
    int id;
    String contrasena;
    String correoInstitucional;

    public Usuario(String contrasena, String correoInstitucional) {
        this.contrasena = contrasena;
        this.correoInstitucional = correoInstitucional;
    }

    public Usuario() {
        this.id = 0;
        this.contrasena = "";
        this.correoInstitucional = "";
    }

    public int getIdUsuario() {
        return id;
    }

    public void setIdUsuario(int id) {
        this.id = id;
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

        if (getContrasena() != null ? !getContrasena().equals(usuario.getContrasena()) : usuario.getContrasena() != null)
            return false;
        return getCorreoInstitucional() != null ? getCorreoInstitucional().equals(usuario.getCorreoInstitucional()) : usuario.getCorreoInstitucional() == null;
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", contrasena='" + contrasena + '\'' +
                ", correoInstitucional='" + correoInstitucional + '\'' +
                '}';
    }

    public Usuario(String nombre, String apellidoPaterno, String apellidoMaterno, String contrasena, String correoInstitucional) {
        super(nombre, apellidoPaterno, apellidoMaterno);
        this.contrasena = contrasena;
        this.correoInstitucional = correoInstitucional;
    }

    public Usuario(String nombre, String apellidoPaterno, String apellidoMaterno, int id, String contrasena, String correoInstitucional) {
        super(nombre, apellidoPaterno, apellidoMaterno);
        this.id = id;
        this.contrasena = contrasena;
        this.correoInstitucional = correoInstitucional;
    }

    public Usuario(String nombre, String apellidoPaterno, String apellidoMaterno) {
        super(nombre, apellidoPaterno, apellidoMaterno);
    }
}
