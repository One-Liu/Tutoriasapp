package uv.fei.tutorias.domain;

// author @liu

public class TutorAcademico extends Persona{
    private int id;
    private Usuario usuario;


    public TutorAcademico(String nombre, String apellidoPaterno, String apellidoMaterno, Usuario usuario) {
        super(nombre, apellidoPaterno, apellidoMaterno);
        this.usuario = usuario;
    }

    public TutorAcademico() {
        this.usuario = new Usuario();
    }

    public TutorAcademico(Persona persona) {
        super(persona.getNombre(), persona.getApellidoPaterno(), persona.getApellidoMaterno());
        usuario = new Usuario();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "TutorAcademico{" +
                "id=" + id +
                ", usuario=" + usuario +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TutorAcademico)) return false;
        if (!super.equals(o)) return false;

        TutorAcademico that = (TutorAcademico) o;

        if (getId() != that.getId()) return false;
        return getUsuario() != null ? getUsuario().equals(that.getUsuario()) : that.getUsuario() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getUsuario() != null ? getUsuario().hashCode() : 0);
        return result;
    }
}