package uv.fei.tutorias.domain;

// author @liu
public class TutorAcademico extends Persona{
    private int id;
    private Usuario usuario;

    public TutorAcademico() {
        this.id = 0;
        this.usuario = new Usuario();
    }

    public TutorAcademico(Persona persona) {
        super(persona.getNombre(), persona.getApellidoPaterno(), persona.getApellidoMaterno());
        this.id = 0;
        usuario = new Usuario();
    }

    public TutorAcademico(int id, Persona persona) {
        super(persona.getNombre(), persona.getApellidoPaterno(), persona.getApellidoMaterno());
        this.id = id;
        usuario = new Usuario();
    }
    
    public TutorAcademico(Persona persona, Usuario usuario) {
        super(persona.getNombre(), persona.getApellidoPaterno(), persona.getApellidoMaterno());
        this.id = 0;
        usuario = usuario;
    }

    public TutorAcademico(String nombre, String apellidoPaterno, String apellidoMaterno) {
        super(nombre,apellidoPaterno,apellidoMaterno);
        this.id = 0;
        this.usuario = new Usuario();
    }

    public TutorAcademico(String nombre, String apellidoPaterno, String apellidoMaterno, Usuario usuario) {
        super(nombre, apellidoPaterno, apellidoMaterno);
        this.id = 0;
        this.usuario = usuario;
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
    public boolean equals(Object obj) {
        if(obj instanceof TutorAcademico) {
            TutorAcademico tmpTutorAcademico = (TutorAcademico)obj;
            if(this.id == tmpTutorAcademico.getId()
                    && this.idPersona == tmpTutorAcademico.getIdPersona()
                    && this.nombre.equals(tmpTutorAcademico.getNombre())
                    && this.apellidoPaterno.equals(tmpTutorAcademico.getApellidoPaterno())
                    && this.apellidoMaterno.equals(tmpTutorAcademico.getApellidoMaterno())
                    && this.usuario.equals(tmpTutorAcademico.getUsuario())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getUsuario() != null ? getUsuario().hashCode() : 0);
        return result;
    }
}