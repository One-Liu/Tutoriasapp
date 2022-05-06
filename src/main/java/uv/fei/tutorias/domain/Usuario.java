package uv.fei.tutorias.domain;

public class Usuario {
    int id;
    String contrasena;
    String correoInstitucional;

    public Usuario(String contrasena, String correoInstitucional) {
        this.contrasena = contrasena;
        this.correoInstitucional = correoInstitucional;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

        if (getId() != usuario.getId()) return false;
        if (getContrasena() != null ? !getContrasena().equals(usuario.getContrasena()) : usuario.getContrasena() != null)
            return false;
        return getCorreoInstitucional() != null ? getCorreoInstitucional().equals(usuario.getCorreoInstitucional()) : usuario.getCorreoInstitucional() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getContrasena() != null ? getContrasena().hashCode() : 0);
        result = 31 * result + (getCorreoInstitucional() != null ? getCorreoInstitucional().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", contrasena='" + contrasena + '\'' +
                ", correoInstitucional='" + correoInstitucional + '\'' +
                '}';
    }
}
